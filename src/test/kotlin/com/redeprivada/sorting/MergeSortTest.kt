package com.redeprivada.sorting

class MergeSortTest : SortingBaseTest() {
    override fun getSorter(): Sortable {
        return MergeSort()
    }
}
