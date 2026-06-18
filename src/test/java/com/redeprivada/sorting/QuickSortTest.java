package com.redeprivada.sorting;

public class QuickSortTest extends SortingBaseTest {
    @Override
    protected Sortable getSorter() {
        return new QuickSort();
    }
}
