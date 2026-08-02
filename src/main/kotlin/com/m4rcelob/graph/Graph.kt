package com.m4rcelob.graph

class Node<T>(val value: T) {
    override fun toString(): String = "N[$value]"
}

class Edge<T>(val from: Node<T>, val to: Node<T>, val weight: Int = 0) {
    override fun toString(): String = "$from -{$weight}-> $to"
}

class Graph<T>(val nodes: MutableSet<Node<T>> = mutableSetOf(), val edges: MutableSet<Edge<T>> = mutableSetOf()) {
    val size: Int
        get() = nodes.size
    fun isEmpty(): Boolean
        = nodes.isEmpty()

    fun getUndirectedNeighbors(node: Node<T>): Set<Pair<Node<T>, Int>> {
        val neighbors = HashSet<Pair<Node<T>, Int>>()
        for (e in edges) {
            if (e.from == node)
                neighbors.add(Pair(e.to, e.weight))
            else if (e.to == node)
                neighbors.add(Pair(e.from, e.weight))
        }
        return neighbors
    }

    fun addDirectedEdge(from: T, to: T, weight: Int = 0) {
        val fromNode: Node<T> = nodes.firstOrNull { it.value == from } ?:
            Node(from).apply { nodes.add(this) }
        val toNode = nodes.firstOrNull { it.value == to } ?:
            Node(to).apply { nodes.add(this) }
        edges.add(Edge(fromNode, toNode, weight))
    }

    fun addUndirectedEdge(a: T, b: T, weight: Int) {
        val nodeA: Node<T> = nodes.firstOrNull { it.value == a } ?:
            Node(a).apply { nodes.add(this) }
        val nodeB = nodes.firstOrNull { it.value == b } ?:
            Node(b).apply { nodes.add(this) }
        edges.add(Edge(nodeA, nodeB, weight))
        edges.add(Edge(nodeB, nodeA, weight))
    }

    fun addUndirectedEdge(a: Node<T>, b: Node<T>, weight: Int) {
        if (!nodes.contains(a))
            nodes.add(a)
        if (!nodes.contains(b))
            nodes.add(b)
        edges.add(Edge(a, b, weight))
        edges.add(Edge(b, a, weight))
    }
}