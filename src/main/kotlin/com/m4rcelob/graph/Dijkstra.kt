package com.m4rcelob.graph

import java.util.PriorityQueue
import kotlin.collections.ArrayDeque

/**
 * Implementation of classic algorithm for finding the shortest path in a weighted graph.
 * The [graph] is a [LinkedHashMap] of nodes where for each key [Int], the node identifier, we have a [Set] of [Pair]
 * that indicates which are the imediate neighbors and the respective cost for reaching them. The first node in the
 * [LinkedHashMap] is origin. The cost must not be negative.
 */
class Dijkstra(val graph: LinkedHashMap<Int, Set<Pair<Int, Int>>>) {
    /**
     * @return The list of node keys to reach [destination], including the first and the last nodes.
     */
    fun shortestPath(destination: Int): List<Int> {
        destinationShortCircuit(destination)?.let { return it }

        val origin = graph.firstEntry().key
        val visited = HashSet<Int>()
        val costs = mutableMapOf<Int, Int>()
        val parents = mutableMapOf<Int, Int>()
        val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second } )

        costs[origin] = 0
        queue.add(Pair(origin, 0))

        while (queue.isNotEmpty()) {
            val closest = queue.poll()
            val node = closest.first
            val nodeCost = closest.second

            if (visited.contains(node))
                continue
            visited.add(node)

            for ((neighbor, weight) in graph[node] ?: emptySet()) {
                require(weight >= 0) { "The edge weight can not be negative" }
                val neighborCost = costs[neighbor] ?: Int.MAX_VALUE
                if (neighborCost > nodeCost + weight) {
                    costs[neighbor] = nodeCost + weight
                    parents[neighbor] = node
                    queue.add(Pair(neighbor, costs.getValue(neighbor)))
                }
            }
        }

        val path = ArrayDeque<Int>()
        var pathNode: Int? = destination
        while (pathNode != null) {
            path.addFirst(pathNode)
            pathNode = parents[pathNode]
        }

        return path
    }

    /**
     * If we don't need to process the graph to get the shortest path to [destination].
     */
    private fun destinationShortCircuit(destination: Int): List<Int>? {
        if (graph.isEmpty())
            return emptyList()

        if (graph.firstEntry().key == destination)
            return listOf(destination)

        if (!graph.values.flatten().map { it.first }.contains(destination))
            return emptyList()

        return null
    }
}