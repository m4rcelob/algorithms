package com.m4rcelob.graph

/**
 * Kruskal's algorithm for minimum spanning tree (MST)
 */
class Kruskals<T>: MSTAlgorithm<T> {
    override fun minimumSpanningTree(graph: Graph<T>): Graph<T> {
        if (graph.size <= 1)
            return graph

        val edges = graph.edges.sortedBy { it.weight }

        val tree = Graph<T>()
        val ds = DisjointSet(graph)

        for (e in edges) {
            if (tree.size == graph.size)
                break
            if (ds.find(e.from) != ds.find(e.to)) {
                ds.union(e.from, e.to)
                tree.addUndirectedEdge(e.from.value, e.to.value, e.weight)
            }
        }

        return if (tree.size == graph.size)
            tree
        else
            Graph()
    }
}