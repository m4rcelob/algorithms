package com.redeprivada.sorting;

public class HeapSortTest extends SortingBaseTest{
    @Override
    protected Sortable getSorter() {
        return new HeapSort();
    }
}
