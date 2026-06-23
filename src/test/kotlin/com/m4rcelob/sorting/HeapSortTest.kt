package com.m4rcelob.sorting

class HeapSortTest : SortingBaseTest() {
    override fun getSorter(): Sortable {
        return HeapSort()
    }
}
