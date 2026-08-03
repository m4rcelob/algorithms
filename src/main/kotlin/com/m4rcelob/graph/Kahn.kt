package com.m4rcelob.graph

/**
 * Kahn's algorithm for topological sorting
 */
class Kahn<T> {
    fun sort(graph: Graph<T>): Graph<T> {
        if (graph.size <= 1)
            return graph

        val indegree = HashMap<Node<T>, Int>()
        val adjacency = HashMap<Node<T>, MutableSet<Pair<Node<T>, Int>>>()
        for (n in graph.nodes) {
            indegree[n] = 0
            adjacency[n] = mutableSetOf()
        }
        for (e in graph.edges) {
            indegree[e.to] = indegree.getValue(e.to) + 1
            adjacency.getValue(e.from).add(Pair(e.to, e.weight))
        }

        // triple of parent, node and weight
        val queue = ArrayDeque<Triple<Node<T>?, Node<T>, Int>>()
        for ((node, count) in indegree) {
            if (count == 0)
                queue.addLast(Triple(null, node, 0))
        }

        val dag = Graph<T>()
        while (queue.isNotEmpty()) {
            val edge = queue.removeFirst()
            val parent = edge.first
            val node = edge.second
            val weight = edge.third

            dag.nodes.add(node)
            if (parent != null)
                dag.edges.add(Edge(parent, node, weight))
            for ((neighbor, weight) in adjacency.getValue(node)) {
                indegree[neighbor] = indegree.getValue(neighbor) - 1
                if (indegree.getValue(neighbor) == 0)
                    queue.addLast(Triple(node, neighbor, weight))
            }
        }

        return if (dag.size == graph.size) dag else Graph()
    }
}