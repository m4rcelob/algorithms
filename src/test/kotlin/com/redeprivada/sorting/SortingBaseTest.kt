package com.redeprivada.sorting

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

abstract class SortingBaseTest {
    private var sorter: Sortable? = null

    protected abstract fun getSorter(): Sortable

    @BeforeEach
    fun setUp() {
        sorter = getSorter()
    }

    @Test
    fun shouldSortReversedArray() {
        val nums = intArrayOf(9, 7, 4, 3, 2)
        sorter!!.sort(nums)
        val expected = intArrayOf(2, 3, 4, 7, 9)
        assert(nums.contentEquals(expected))
    }

    @Test
    fun shouldSortAlreadySortedArray() {
        val nums = intArrayOf(2, 3, 4, 7, 9)
        sorter!!.sort(nums)
        val expected = intArrayOf(2, 3, 4, 7, 9)
        assert(nums.contentEquals(expected))
    }

    @Test
    fun shouldSortSingleElementArray() {
        val nums = intArrayOf(3)
        sorter!!.sort(nums)
        val expected = intArrayOf(3)
        assert(nums.contentEquals(expected))
    }

    @Test
    fun shouldSortEmptyArray() {
        val nums = intArrayOf()
        sorter!!.sort(nums)
        val expected = intArrayOf()
        assert(nums.contentEquals(expected))
    }

    @Test
    fun shouldSortArrayWithRepeatedElements() {
        val nums = intArrayOf(3, 3, 3)
        sorter!!.sort(nums)
        val expected = intArrayOf(3, 3, 3)
        assert(nums.contentEquals(expected))
    }

    @Test
    fun shouldSortRandomArray() {
        val nums = IntArray(1000) {
            Random.nextInt(0, 1000000)
        }
        val expected = nums.clone()
        sorter!!.sort(nums)
        expected.sort()
        assert(nums.contentEquals(expected))
    }
}
