package com.redeprivada.sorting

class QuickSortTest : SortingBaseTest() {
    override fun getSorter(): Sortable {
        return QuickSort()
    }
}
