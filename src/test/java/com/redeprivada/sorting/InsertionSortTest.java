package com.redeprivada.sorting;

public class InsertionSortTest extends SortingBaseTest {

    @Override
    protected Sortable getSorter() {
        return new InsertionSort();
    }
}
