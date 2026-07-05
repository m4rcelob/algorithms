package com.m4rcelob.btree


data class Node<T: Comparable<T>> (
    var p: Node<T>? = null,
    var left: Node<T>? = null,
    var right: Node<T>? = null,
    var key: T
) {
    override fun hashCode(): Int {
        return this.key.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node<T>) return false
        return this.key == other.key
    }

    override fun toString(): String {
        return "Node($key)"
    }
}

class BST <T: Comparable<T>> (val root: Node<T>) {
    fun inorderTreeWalk(root: Node<T>?, block: (current: Node<T>?) -> Unit = {}) {
        if (root != null) {
            inorderTreeWalk(root.left, block)
            block(root)
            inorderTreeWalk(root.right, block)
        }
    }

    fun treeSearch(root: Node<T>?, key: T): Node<T>? {
        if (root == null || key == root.key)
            return root
        return if (key < root.key)
            treeSearch(root.left, key)
        else
            treeSearch(root.right, key)
    }

    fun iterativeTreeSearch(root: Node<T>?, key: T): Node<T>? {
        var node = root
        while(node != null && node.key != key) {
            node = if (key < node.key)
                node.left
            else
                node.right
        }
        return node
    }

    fun treeMinimum(root: Node<T>?): Node<T>? {
        var node = root
        while (node?.left != null)
            node = node.left
        return node
    }

    fun treeMaximum(root: Node<T>?): Node<T>? {
        var node = root
        while (node?.right != null)
            node = node.right
        return node
    }

    fun treeSuccessor(node: Node<T>?): Node<T>? {
        if (node == null)
            return node
        return if (node.right != null)
            treeMinimum(node.right)
        else {
            var current = node
            var parent = node.p
            while (parent != null && parent.right == current) {
                current = parent
                parent = parent.p
            }
            parent
        }
    }

    fun treePredecessor(node: Node<T>?): Node<T>? {
        if (node == null)
            return node
        return if (node.left != null)
            treeMaximum(node.left)
        else {
            var current = node
            var parent = node.p
            while (parent != null && parent.left == current) {
                current = parent
                parent = parent.p
            }
            parent
        }
    }
}