package com.m4rcelob.graph

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DijkstraTest {
    @Test
    fun shouldFindShortestPathForEmptyGraph() {
        val graph = Graph<Int>()
        val dijkstra = Dijkstra(graph)
        Assertions.assertTrue(dijkstra.shortestPath(Node(0), Node(0)).isEmpty())
    }

    @Test
    fun shouldFindShortestPathForSingleNodeGraph() {
        val graph = Graph<Int>()
        graph.nodes.add(Node(1))
        val dijkstra = Dijkstra(graph)
        val tree = dijkstra.shortestPath(Node(1), Node(1))
        tree.size shouldBe 1
    }

    @Test
    fun shouldNotFindPathForDisconnectedNode() {
        val graph = Graph<Int>()
        graph.addDirectedEdge(1, 2, 1)
        graph.nodes.add(Node(3))
        val dijkstra = Dijkstra(graph)
        val tree = dijkstra.shortestPath(Node(1), Node(3))
        tree.isEmpty() shouldBe true
    }

    @Test
    fun shouldNotFindPathForNodeNotInGraph() {
        val graph = Graph<Int>()
        graph.addDirectedEdge(1, 2, 1)
        val dijkstra = Dijkstra(graph)
        val tree = dijkstra.shortestPath(Node(1), Node(3))
        tree.isEmpty() shouldBe true
    }

    @Test
    fun shouldFindShortestPath() {
        val graph = Graph<Int>()
        graph.addDirectedEdge(1, 2, 5)
        graph.addDirectedEdge(1, 3, 2)
        graph.addDirectedEdge(2, 4, 2)
        graph.addDirectedEdge(3, 2, 1)
        graph.addDirectedEdge(3, 4, 6)

        val dijkstra = Dijkstra(graph)
        val tree = dijkstra.shortestPath(Node(1), Node(4))
        tree.size shouldBe 4
        tree.edges.firstOrNull { it.from == Node(1) && it.to == Node(3) } shouldNotBe null
        tree.edges.firstOrNull { it.from == Node(3) && it.to == Node(2) } shouldNotBe null
        tree.edges.firstOrNull { it.from == Node(2) && it.to == Node(4) } shouldNotBe null
    }

    @Test
    fun shouldFindShortestPathForGraphWithNodesNotInThePath() {
        val graph = Graph<Int>()
        graph.addDirectedEdges(arrayOf(
            Triple(1, 2, 2),
            Triple(1, 3, 4),
            Triple(1, 10, 100),
            Triple(2, 3, 1),
            Triple(2, 4, 7),
            Triple(3, 5, 3),
            Triple(4, 6, 1),
            Triple(5, 4, 2),
            Triple(5, 6, 5)
        ))

        val dijkstra = Dijkstra(graph)
        val tree = dijkstra.shortestPath(Node(1), Node(6))
        tree.size shouldBe 6
        tree.edges.firstOrNull { it.from == Node(3) && it.to == Node(5) } shouldNotBe null
        tree.edges.firstOrNull { it.from == Node(5) && it.to == Node(4) } shouldNotBe null
        tree.edges.firstOrNull { it.from == Node(4) && it.to == Node(6) } shouldNotBe null
    }

}