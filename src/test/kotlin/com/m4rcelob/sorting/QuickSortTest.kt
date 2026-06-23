package com.m4rcelob.sorting

class QuickSortTest : SortingBaseTest() {
    override fun getSorter(): Sortable {
        return QuickSort()
    }
}
