package com.m4rcelob.sorting

class InsertionSortTest : SortingBaseTest() {
    override fun getSorter(): Sortable {
        return InsertionSort()
    }
}
