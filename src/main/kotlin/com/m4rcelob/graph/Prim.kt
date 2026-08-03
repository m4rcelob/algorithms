package com.m4rcelob.graph

import java.util.*

/**
 * Prim's algorithm for minimum spanning tree (MST)
 */
class Prim<T>: MSTAlgorithm<T> {
    override fun minimumSpanningTree(graph: Graph<T>): Graph<T> {
        if (graph.isEmpty())
            return Graph()

        val tree = Graph<T>()
        val queue = PriorityQueue<Pair<Node<T>, Int>>(compareBy { it.second })
        queue.add(Pair(graph.nodes.first(), 0))

        var previousInsertedNode: Node<T>? = null
        while (tree.size < graph.size) {
            if (queue.isEmpty())
                return Graph()
            val nodeAndWeight = queue.poll()
            val node = nodeAndWeight.first

            if (tree.nodes.contains(node))
                continue

            tree.nodes.add(node)
            if (previousInsertedNode != null)
                tree.addUndirectedEdge(previousInsertedNode, node, nodeAndWeight.second)
            previousInsertedNode = node

            for (neighbor in graph.getUndirectedNeighbors(node)) {
                if (!tree.nodes.contains(neighbor.first))
                    queue.add(neighbor)
            }
        }
        return tree
    }
}