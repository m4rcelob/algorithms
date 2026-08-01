package com.m4rcelob.graph

class PrimsTest: MinimumSpanningTreeBaseTest() {
    override fun getAlgorithm(): MSTAlgorithm<Int> {
        return Prims()
    }
}