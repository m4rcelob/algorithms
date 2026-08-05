package com.m4rcelob.graph

import java.util.*

/**
 * Implementation of classic algorithm for finding the shortest path in a weighted graph.
 */
class Dijkstra<T>(val graph: Graph<T>) {
    /**
     * @return The tree for the path, including origin and destination.
     */
    fun shortestPath(origin: Node<T>, destination: Node<T>): Graph<T> {
        destinationShortCircuit(origin, destination)?.let { return it }

        val adjacency = mutableMapOf<Node<T>, MutableSet<Pair<Node<T>, Int>>>()
        val costs = mutableMapOf<Node<T>, Int>()

        for (n in graph.nodes) {
            adjacency[n] = mutableSetOf()
            costs[n] = Int.MAX_VALUE
        }

        if (populateAdjacencyList(adjacency, destination) == 0)
            return Graph()

        val visited = HashSet<Node<T>>()
        val parents = mutableMapOf<Node<T>, Node<T>>()
        val queue = PriorityQueue<Pair<Node<T>, Int>>(compareBy { it.second } )

        costs[origin] = 0
        queue.add(Pair(origin, 0))

        while (queue.isNotEmpty()) {
            val closest = queue.poll()
            val node = closest.first
            val nodeCost = closest.second

            if (visited.contains(node))
                continue
            visited.add(node)

            for ((neighbor, weight) in adjacency.getValue(node)) {
                require(weight >= 0) { "The edge weight can not be negative" }
                val neighborCost = costs.getValue(neighbor)
                if (neighborCost > nodeCost + weight) {
                    costs[neighbor] = nodeCost + weight
                    parents[neighbor] = node
                }
                queue.add(Pair(neighbor, costs.getValue(neighbor)))
            }
        }

        val tree = Graph<T>()
        var node: Node<T>? = destination
        while (node != null) {
            val parent = parents[node]
            if (parent != null) {
                tree.addDirectedEdge(parent, node)
            } else {
                tree.nodes.add(node)
            }
            node = parent
        }
        return tree
    }

    /**
     * If we don't need to process the graph to get the shortest path to [destination].
     */
    private fun destinationShortCircuit(origin: Node<T>, destination: Node<T>): Graph<T>? {
        if (graph.isEmpty())
            return Graph()

        if (origin == destination)
            return Graph(mutableSetOf(origin))

        return null
    }

    private fun populateAdjacencyList(adjacency: Map<Node<T>, MutableSet<Pair<Node<T>, Int>>>, destination: Node<T>): Int {
        var destinationIndegree = 0
        for (e in graph.edges) {
            adjacency.getValue(e.from).add(Pair(e.to, e.weight))
            if (e.to == destination)
                destinationIndegree++
        }
        return destinationIndegree
    }
}