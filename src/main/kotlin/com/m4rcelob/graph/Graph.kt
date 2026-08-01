package com.m4rcelob.graph

class Node<T>(val value: T)

class Edge<T>(val a: Node<T>, val b: Node<T>, val weight: Int)

class Graph<T>(val nodes: MutableSet<Node<T>> = mutableSetOf(), val edges: MutableSet<Edge<T>> = mutableSetOf()) {
    val size: Int
        get() = nodes.size
    fun isEmpty(): Boolean
        = nodes.isEmpty()

    fun getNeighbors(node: Node<T>): Set<Pair<Node<T>, Int>> {
        val neighbors = HashSet<Pair<Node<T>, Int>>()
        for (e in edges) {
            if (e.a == node)
                neighbors.add(Pair(e.b, e.weight))
            else if (e.b == node)
                neighbors.add(Pair(e.a, e.weight))
        }
        return neighbors
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