package com.redeprivada.sorting

class CountingSortTest : SortingBaseTest() {
    override fun getSorter(): Sortable {
        return CountingSort(1000)
    }
}