package com.m4rcelob.graph

fun interface MSTAlgorithm<T> {
    fun minimumSpanningTree(graph: Graph<T>): Graph<T>
}