package com.m4rcelob.mst

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PrimsTest {
    @Test
    fun shouldFindMSTForEmptyGraph() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        val prims = Prims(graph)
        prims.minimumSpanningTree().size shouldBe 0
    }

    @Test
    fun shouldFindMSTForSingleNodeGraph() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        graph[1] = emptySet()
        val prims = Prims(graph)
        val tree = prims.minimumSpanningTree()
        tree.size shouldBe 1
        tree.first().first shouldBe 1
        tree.first().second shouldBe 0
    }

    @Test
    fun shouldNotFindMSTForDisconnectedGraph() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        graph[1] = setOf(Pair(2, 1))
        graph[2] = setOf(Pair(1, 1))
        graph[3] = emptySet()

        val prims = Prims(graph)
        prims.minimumSpanningTree() shouldBe emptyList()
    }

    @Test
    fun shouldFindMST() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        graph[1] = setOf(Pair(2, 5), Pair(3, 2))
        graph[2] = setOf(Pair(1, 5), Pair(4, 2))
        graph[3] = setOf(Pair(1, 2), Pair(2, 1), Pair(4, 6))
        graph[4] = setOf(Pair(2, 2), Pair(3, 6))
        val prims = Prims(graph)
        val tree = prims.minimumSpanningTree()

        tree[0].first shouldBe 1
        tree[0].second shouldBe 0
        tree[1].first shouldBe 3
        tree[1].second shouldBe 2
        tree[2].first shouldBe 2
        tree[2].second shouldBe 1
        tree[3].first shouldBe 4
        tree[3].second shouldBe 2
    }
}