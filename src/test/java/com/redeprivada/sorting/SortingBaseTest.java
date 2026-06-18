package com.redeprivada.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

abstract class SortingBaseTest {
    private Sortable sorter;

    protected abstract Sortable getSorter();

    @BeforeEach
    void setUp() {
        sorter = getSorter();
    }

    @Test
    void shouldSortReversedArray() {
        int[] nums = {9, 7, 4, 3, 2};
        sorter.sort(nums);
        int[] expected = {2, 3, 4, 7, 9};
        assertArrayEquals(expected, nums);
    }

    @Test
    void shouldSortAlreadySortedArray() {
        int[] nums = {2, 3, 4, 7, 9};
        sorter.sort(nums);
        int[] expected = {2, 3, 4, 7, 9};
        assertArrayEquals(expected, nums);
    }

    @Test
    void shouldSortSingleElementArray() {
        int[] nums = {3};
        sorter.sort(nums);
        int[] expected = {3};
        assertArrayEquals(expected, nums);
    }

    @Test
    void shouldSortEmptyArray() {
        int[] nums = {};
        sorter.sort(nums);
        int[] expected = {};
        assertArrayEquals(expected, nums);
    }

    @Test
    void shouldSortArrayWithRepeatedElements() {
        int[] nums = {3, 3, 3};
        sorter.sort(nums);
        int[] expected = {3, 3, 3};
        assertArrayEquals(expected, nums);
    }

    @Test
    void shouldSortRandomArray() {
        int[] nums = Stream.iterate(1, n -> ThreadLocalRandom.current().nextInt(0, 1000000))
                .limit(1000)
                .mapToInt(Integer::intValue)
                .toArray();
        int[] expected = nums.clone();
        sorter.sort(nums);
        Arrays.sort(expected);
        assertArrayEquals(expected, nums);
    }
}
