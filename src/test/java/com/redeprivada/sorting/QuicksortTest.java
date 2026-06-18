package com.redeprivada.sorting;

public class QuicksortTest extends SortingBaseTest {
    @Override
    protected Sortable getSorter() {
        return new Quicksort();
    }
}
