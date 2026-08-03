package com.m4rcelob.graph

class PrimTest: MinimumSpanningTreeBaseTest() {
    override fun getAlgorithm(): MSTAlgorithm<Int> {
        return Prim()
    }
}