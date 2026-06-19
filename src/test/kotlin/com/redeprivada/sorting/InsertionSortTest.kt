package com.redeprivada.sorting

class InsertionSortTest : SortingBaseTest() {
    override fun getSorter(): Sortable {
        return InsertionSort()
    }
}
