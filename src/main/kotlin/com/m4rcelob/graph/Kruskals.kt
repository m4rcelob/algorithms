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
            if (ds.find(e.a) != ds.find(e.b)) {
                ds.union(e.a, e.b)
                tree.addUndirectedEdge(e.a.value, e.b.value, e.weight)
            }
        }

        return if (tree.size == graph.size)
            tree
        else
            Graph()
    }
}