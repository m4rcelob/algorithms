package com.m4rcelob.mst

import java.util.*

/**
 * Prim's algorithm for minimum spanning tree (MST)
 * [graph] is a [Map] of nodes to a [Set] containing each neighbor and respective
 * weight
 */
class Prims(val graph: Map<Int, Set<Pair<Int, Int>>>) {
    val n = graph.keys.size

    /**
     * @return [List] of vertices with weight to be reached
     */
    fun minimumSpanningTree(): List<Pair<Int, Int>> {
        if (graph.isEmpty())
            return emptyList()

        val tree = mutableListOf<Pair<Int, Int>>()
        val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        val visited = HashSet<Int>()


        // Arbitrary node
        queue.add(Pair(graph.keys.first(), 0))

        while (tree.size < n) {
            if (queue.isEmpty())
                return emptyList()
            val nodeAndWeight = queue.poll()
            val node = nodeAndWeight.first
            if (visited.contains(node))
                continue
            visited.add(node)
            tree.add(nodeAndWeight)

            for (neighbor in (graph[node] ?: emptySet())) {
                if (!visited.contains(neighbor.first))
                    queue.add(neighbor)
            }
        }
        return tree
    }
}