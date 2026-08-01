package com.m4rcelob.graph

class DisjointSet<T>(graph: Graph<T>) {
    val root = HashMap<Node<T>, Node<T>>(graph.size)
    val rank = HashMap<Node<T>, Int>(graph.size)

    init {
        for (n in graph.nodes) {
            root[n] = n
            rank[n] = 1
        }
    }

    fun find(node: Node<T>): Node<T> {
        val rootNode = root.getValue(node)
        if (rootNode != node)
            root[node] = find(rootNode) // path compression
        return root.getValue(node)
    }

    fun union(a: Node<T>, b: Node<T>) {
        val rootA = find(a)
        val rootB = find(b)
        val rankA = rank.getValue(rootA)
        val rankB = rank.getValue(rootB)

        if (rootA != rootB) {
            if (rankA > rankB)
                root[rootB] = rootA
            else if (rankA < rankB)
                root[rootA] = rootB
            else {
                root[rootB] = rootA
                rank[rootA] = rankA + 1
            }
        }
    }
}