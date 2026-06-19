package com.redeprivada.sorting

class HeapSortTest : SortingBaseTest() {
    override fun getSorter(): Sortable {
        return HeapSort()
    }
}
