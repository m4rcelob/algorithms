package com.m4rcelob.graph

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DijkstraTest {
    @Test
    fun shouldFindShortestPathForEmptyGraph() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        val dijkstra = Dijkstra(graph)
        Assertions.assertTrue(dijkstra.shortestPath(0).isEmpty())
    }

    @Test
    fun shouldFindShortestPathForSingleNodeGraph() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        graph[1] = emptySet()
        val dijkstra = Dijkstra(graph)
        Assertions.assertEquals(1, dijkstra.shortestPath(1).first())
    }

    @Test
    fun shouldNotFindPathForDisconnectedNode() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        graph[1] = setOf(Pair(2, 1))
        graph[3] = emptySet()
        val dijkstra = Dijkstra(graph)
        Assertions.assertTrue(dijkstra.shortestPath(3).isEmpty())
    }

    @Test
    fun shouldNotFindPathForNodeNotInGraph() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        graph[1] = setOf(Pair(2, 1))
        val dijkstra = Dijkstra(graph)
        Assertions.assertTrue(dijkstra.shortestPath(3).isEmpty())
    }

    @Test
    fun shouldFindShortestPath() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        graph[1] = setOf(Pair(2, 5), Pair(3, 2))
        graph[2] = setOf(Pair(4, 2))
        graph[3] = setOf(Pair(2, 1), Pair(4, 6))
        val dijkstra = Dijkstra(graph)
        val path = dijkstra.shortestPath(4)
        Assertions.assertEquals(1, path.first())
        Assertions.assertEquals(3, path[1])
        Assertions.assertEquals(2, path[2])
        Assertions.assertEquals(4, path.last())
    }

    @Test
    fun shouldFindShortestPathForGraphWithNodesNotInThePath() {
        val graph = LinkedHashMap<Int, Set<Pair<Int, Int>>>()
        graph[1] = setOf(Pair(2, 2), Pair(3, 4), Pair(10, 100))
        graph[2] = setOf(Pair(3, 1), Pair(4, 7))
        graph[3] = setOf(Pair(5, 3))
        graph[4] = setOf(Pair(6, 1))
        graph[5] = setOf(Pair(4, 2), Pair(6, 5))

        val dijkstra = Dijkstra(graph)
        val path = dijkstra.shortestPath(6)
        Assertions.assertEquals(1, path[0])
        Assertions.assertEquals(2, path[1])
        Assertions.assertEquals(3, path[2])
        Assertions.assertEquals(5, path[3])
        Assertions.assertEquals(4, path[4])
        Assertions.assertEquals(6, path.last())
    }

}