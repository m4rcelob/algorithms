package com.m4rcelob.graph

class KruskalsTest: MinimumSpanningTreeBaseTest() {
    override fun getAlgorithm(): MSTAlgorithm<Int> {
        return Kruskals()
    }
}