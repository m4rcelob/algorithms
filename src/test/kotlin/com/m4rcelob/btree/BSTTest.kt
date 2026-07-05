package com.m4rcelob.btree

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BSTTest {
    @Test
    fun shouldWalkInOrder() {
        val tree = testTree()
        val keys = mutableListOf<Int>()
        tree.inorderTreeWalk(tree.root, { n ->
            n?.let { keys.add(it.key) }
        })
        val expected = intArrayOf(2, 3, 4, 6, 7, 9, 13, 15, 17, 18, 20)
        assert(keys.toIntArray().contentEquals(expected))
    }

    @Test
    fun shouldFindRecursively() {
        val tree = testTree()
        tree.treeSearch(tree.root, 9)?.key shouldBe 9
        tree.treeSearch(tree.root, 15) shouldBe tree.root
        tree.treeSearch(tree.root, 99) shouldBe null
        tree.treeSearch(tree.root.right, 9) shouldBe null
    }

    @Test
    fun shouldFindIteratively() {
        val tree = testTree()
        tree.iterativeTreeSearch(tree.root, 9)?.key shouldBe 9
        tree.iterativeTreeSearch(tree.root, 15) shouldBe tree.root
        tree.iterativeTreeSearch(tree.root, 99) shouldBe null
        tree.iterativeTreeSearch(tree.root.right, 9) shouldBe null
    }

    @Test
    fun shouldFindMinimum() {
        val tree = testTree()
        tree.treeMinimum(null) shouldBe null
        tree.treeMinimum(tree.root)?.key shouldBe 2
        tree.treeMinimum(tree.treeSearch(tree.root, 2))?.key shouldBe 2
        tree.treeMinimum(tree.treeSearch(tree.root, 18))?.key shouldBe 17
    }

    @Test
    fun shouldFindMaximum() {
        val tree = testTree()
        tree.treeMaximum(null) shouldBe null
        tree.treeMaximum(tree.root)?.key shouldBe 20
        tree.treeMaximum(tree.treeSearch(tree.root, 2))?.key shouldBe 2
        tree.treeMaximum(tree.treeSearch(tree.root, 13))?.key shouldBe 13
    }

    @Test
    fun shouldFindSuccessor() {
        val tree = testTree()
        tree.treeSuccessor(null) shouldBe null
        tree.treeSuccessor(tree.root)?.key shouldBe 17
        tree.treeSuccessor(tree.treeSearch(tree.root, 2))?.key shouldBe 3
        tree.treeSuccessor(tree.treeSearch(tree.root, 13))?.key shouldBe 15
        tree.treeSuccessor(tree.treeSearch(tree.root, 20)) shouldBe null
    }

    @Test
    fun shouldFindPredecessor() {
        val tree = testTree()
        tree.treePredecessor(null) shouldBe null
        tree.treePredecessor(tree.root)?.key shouldBe 13
        tree.treePredecessor(tree.treeSearch(tree.root, 2)) shouldBe null
        tree.treePredecessor(tree.treeSearch(tree.root, 17))?.key shouldBe 15
        tree.treePredecessor(tree.treeSearch(tree.root, 20))?.key shouldBe 18
    }

    private fun testTree(): BST<Int> {
        val n2 = Node(null, null, null, 2)
        val n3 = Node(null, null, null, 3)
        val n4 = Node(null, null, null, 4)
        val n6 = Node(null, null, null, 6)
        val n7 = Node(null, null, null, 7)
        val n9 = Node(null, null, null, 9)
        val n13 = Node(null, null, null, 13)
        val n15 = Node(null, null, null, 15)
        val n17 = Node(null, null, null, 17)
        val n18 = Node(null, null, null, 18)
        val n20 = Node(null, null, null, 20)
        n2.p = n3
        n3.left = n2
        n4.p = n3
        n3.right = n4
        n3.p = n6
        n6.left = n3
        n9.p = n13
        n13.left = n9
        n13.p = n7
        n7.right = n13
        n7.p = n6
        n6.right = n7
        n6.p = n15
        n15.left = n6
        n17.p = n18
        n18.left = n17
        n20.p = n18
        n18.right = n20
        n18.p = n15
        n15.right = n18
        return BST(n15)
    }
}