package com.m4rcelob.graph

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class KahnsTest {
    private val topologicalSorter = Kahns<Int>()

    @Test
    fun shouldSortEmptyGraph() {
        val graph = Graph<Int>()
        topologicalSorter.sort(graph).size shouldBe 0
    }

    @Test
    fun shouldSortSingleNodeGraph() {
        val graph = Graph<Int>()
        graph.nodes.add(Node(1))
        val dag = topologicalSorter.sort(graph)
        dag.size shouldBe 1
        dag.nodes.first().value shouldBe 1
        dag.edges.size shouldBe 0
    }

    @Test
    fun shouldNotFindPathForDisconnectedGraph() {
        val graph = Graph<Int>()
        graph.nodes.addAll(setOf(Node(1), Node(2), Node(3)))
        graph.addDirectedEdge(1, 2, 1)
        topologicalSorter.sort(graph).isEmpty() shouldBe true
    }

    @Test
    fun shouldFindPath() {
        val graph = Graph<Int>()
        graph.addDirectedEdge(0, 1)
        graph.addDirectedEdge(0, 2)
        graph.addDirectedEdge(1, 3)
        graph.addDirectedEdge(2, 3)

        val dag = topologicalSorter.sort(graph)

        dag.size shouldBe 4
        dag.edges.size shouldBe 3
    }

    @Test
    fun shouldNotFindPath() {
        val graph = Graph<Int>()
        graph.addDirectedEdge(0, 1)
        graph.addDirectedEdge(0, 2)
        graph.addDirectedEdge(1, 2)
        graph.addDirectedEdge(2, 3)
        graph.addDirectedEdge(3, 1)
        graph.addDirectedEdge(3, 4)

        val dag = topologicalSorter.sort(graph)

        dag.size shouldBe 4
        dag.edges.size shouldBe 3
    }
}