package com.redeprivada.sorting;

public class MergeSortTest extends SortingBaseTest {
    @Override
    protected Sortable getSorter() {
        return new MergeSort();
    }
}
