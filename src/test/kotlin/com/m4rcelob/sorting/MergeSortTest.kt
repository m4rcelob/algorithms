package com.m4rcelob.sorting

class MergeSortTest : SortingBaseTest() {
    override fun getSorter(): Sortable {
        return MergeSort()
    }
}
