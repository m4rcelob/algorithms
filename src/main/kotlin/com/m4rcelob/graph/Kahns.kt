package com.m4rcelob.graph

/**
 * Kahn's algorithm for topological sorting
 */
class Kahns<T> {
    fun sort(graph: Graph<T>): Graph<T> {
        if (graph.size <= 1)
            return graph

        val indegree = HashMap<Node<T>, Int>()
        val adjacency = HashMap<Node<T>, MutableSet<Node<T>>>()
        for (n in graph.nodes) {
            indegree[n] = 0
            adjacency[n] = mutableSetOf()
        }
        for (e in graph.edges) {
            indegree[e.to] = indegree.getValue(e.to) + 1
            adjacency.getValue(e.from).add(e.to)
        }

        val queue = ArrayDeque<Node<T>>()
        for ((node, count) in indegree) {
            if (count == 0)
                queue.addLast(node)
        }

        val dag = Graph<T>()
        var lastNode: Node<T>? = null
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            dag.nodes.add(node)
            if (lastNode != null)
                dag.edges.add(Edge(lastNode, node))
            for (neighbor in adjacency.getValue(node)) {
                indegree[neighbor] = indegree.getValue(neighbor) - 1
                if (indegree.getValue(neighbor) == 0)
                    queue.addLast(neighbor)
            }
            lastNode = node
        }

        return if (dag.size == graph.size) dag else Graph()
    }
}