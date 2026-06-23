package com.m4rcelob.dijkstra

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
        if (graph.isEmpty())
            return emptyList()

        val origin = graph.firstEntry().key
        if (origin == destination)
            return listOf(destination)

        if (!graph.values.flatten().map { it.first }.contains(destination))
            return emptyList()

        val visitedNodes = mutableListOf<Int>()
        val costs = mutableMapOf<Int, Int>()
        val parents = mutableMapOf<Int, Int>()

        costs[origin] = 0
        // Set costs for origin's neighbors
        graph[origin]?.forEach { neighbor ->
            costs[neighbor.first] = neighbor.second
        }

        var node = origin
        while(node != null) {
            val neighbors = graph[node]

            neighbors?.forEach { (neighbor, cost) ->
                require(cost >= 0) { "The cost can not be negative" }
                val costFromStartingNode = (costs[node] ?: 0) + cost
                val minCost = costs[neighbor] ?: Int.MAX_VALUE
                if (costFromStartingNode <= minCost) {
                    costs[neighbor] = costFromStartingNode
                    parents[neighbor] = node
                }
            }
            visitedNodes.add(node)
            node = costs.filter { !visitedNodes.contains(it.key) }.minByOrNull { it.value }?.key
        }

        val path = ArrayDeque<Int>()
        path.addFirst(destination)
        var parent = parents[destination]
        while (parent != null) {
            path.addFirst(parent)
            parent = parents[parent]
        }

        return path
    }


}