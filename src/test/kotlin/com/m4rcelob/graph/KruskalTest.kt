package com.m4rcelob.graph

class KruskalTest: MinimumSpanningTreeBaseTest() {
    override fun getAlgorithm(): MSTAlgorithm<Int> {
        return Kruskal()
    }
}