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
        destinationShortCircuit(destination)?.let { return it }

        val origin = graph.firstEntry().key
        val visitedNodes = mutableListOf<Int>()
        val costs = mutableMapOf<Int, Int>()
        val parents = mutableMapOf<Int, Int>()

        costs[origin] = 0
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