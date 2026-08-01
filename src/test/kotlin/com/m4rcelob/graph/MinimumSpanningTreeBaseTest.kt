package com.m4rcelob.graph

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class MinimumSpanningTreeBaseTest {
    private var mstCalculator: MSTAlgorithm<Int>? = null

    protected abstract fun getAlgorithm(): MSTAlgorithm<Int>

    @BeforeEach
    fun setUp() {
        mstCalculator = getAlgorithm()
    }

    @Test
    fun shouldFindMSTForEmptyGraph() {
        val graph = Graph<Int>()
        mstCalculator!!.minimumSpanningTree(graph).size shouldBe 0
    }

    @Test
    fun shouldFindMSTForSingleNodeGraph() {
        val graph = Graph<Int>()
        graph.nodes.add(Node(1))
        val tree = mstCalculator!!.minimumSpanningTree(graph)
        tree.size shouldBe 1
        tree.nodes.first().value shouldBe 1
        tree.edges.size shouldBe 0
    }

    @Test
    fun shouldNotFindMSTForDisconnectedGraph() {
        val graph = Graph<Int>()
        graph.nodes.addAll(setOf(Node(1), Node(2), Node(3)))
        graph.addUndirectedEdge(1, 2, 1)

        mstCalculator!!.minimumSpanningTree(graph).isEmpty() shouldBe true
    }

    @Test
    fun shouldFindMST() {
        val graph = Graph<Int>()
        graph.addUndirectedEdge(1, 2, 5)
        graph.addUndirectedEdge(1, 3, 2)
        graph.addUndirectedEdge(2, 3, 1)
        graph.addUndirectedEdge(2, 4, 2)
        graph.addUndirectedEdge(3, 4, 6)

        val tree = mstCalculator!!.minimumSpanningTree(graph)

        tree.size shouldBe 4
        tree.edges.size shouldBe 6 // bidirectional
        tree.edges.sumOf { it.weight } shouldBe 10
    }
}