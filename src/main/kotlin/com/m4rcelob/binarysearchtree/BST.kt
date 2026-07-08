package com.m4rcelob.binarysearchtree

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
        return "n($key)"
    }
}

class BST <T: Comparable<T>> (var root: Node<T>?) {
    fun inorderTreeWalk(root: Node<T>?, block: (current: Node<T>?) -> Unit = {}) {
        if (root != null) {
            inorderTreeWalk(root.left, block)
            block(root)
            inorderTreeWalk(root.right, block)
        }
    }

    fun preorderTreeWalk(root: Node<T>?, block: (current: Node<T>?) -> Unit = {}) {
        if (root != null) {
            block(root)
            preorderTreeWalk(root.left, block)
            preorderTreeWalk(root.right, block)
        }
    }

    fun breadthFirstWalk(block: (current: Node<T>?) -> Unit = {}) {
        val toBeVisited = mutableListOf(root)

        while (toBeVisited.isNotEmpty()) {
            val node = toBeVisited.removeFirst()
            block(node)
            if (node != null) {
                toBeVisited.add(node.left)
                toBeVisited.add(node.right)
            }
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

    fun treeInsert(tree: BST<T>, key: T) {
        var pointer: Node<T>? = tree.root
        var parent: Node<T>? = null

        while (pointer != null) {
            parent = pointer
            pointer = if (key < pointer.key)
                pointer.left
            else
                pointer.right
        }
        val inserted = Node(parent, null, null, key)
        if (parent == null)
            tree.root = inserted
        else if (inserted.key < parent.key)
            parent.left = inserted
        else
            parent.right = inserted
    }

    private fun transplant(tree: BST<T>, destination: Node<T>, subtree: Node<T>?) {
        if (destination.p == null)
            tree.root = subtree
        else if (destination == destination.p?.left)
            destination.p?.left = subtree
        else
            destination.p?.right = subtree
        if (subtree != null)
            subtree.p = destination.p
    }

    fun treeDelete(tree: BST<T>, node: Node<T>) {
        if (node.left == null)
            transplant(tree, node, node.right)
        else if (node.right == null)
            transplant(tree, node, node.left)
        else {
            val successor = treeMinimum(node.right) ?: error("Node must have a successor, because right != null")
            if (successor != node.right) {
                transplant(tree, successor, successor.right)
                successor.right = node.right
                successor.right?.p = successor
            }
            transplant(tree, node, successor)
            successor.left = node.left
            successor.left?.p = successor
        }
    }

    override fun toString(): String {
        val keys = mutableListOf<T?>()
        breadthFirstWalk { node ->
            keys.add(node?.key)
        }
        // Remove trailing nulls
        while (keys.isNotEmpty()) {
            if (keys.last() == null)
                keys.removeLast()
            else
                break
        }
        return keys.joinToString(", ")
    }

    fun orderedKeys(): List<T?> {
        val keys = mutableListOf<T?>()
        inorderTreeWalk(root) { node ->
            keys.add(node?.key)
        }
        return keys
    }
}