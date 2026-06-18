package com.redeprivada.sorting;

public class InsertionsortTest extends SortingBaseTest {

    @Override
    protected Sortable getSorter() {
        return new Insertionsort();
    }
}
