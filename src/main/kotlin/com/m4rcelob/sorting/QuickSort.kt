package com.m4rcelob.sorting

class QuickSort : Sortable {
    override fun sort(nums: IntArray) {
        sort(nums, 0, nums.size - 1)
    }

    private fun sort(nums: IntArray, start: Int, end: Int) {
        if (start < end) {
            val pivot = partition(nums, start, end)
            sort(nums, start, pivot - 1)
            sort(nums, pivot + 1, end)
        }
    }

    private fun partition(nums: IntArray, start: Int, end: Int): Int {
        var i = start - 1
        val pivot = nums[end]
        for (j in start..<end) {
            if (nums[j] < pivot) {
                i++
                val temp = nums[i]
                nums[i] = nums[j]
                nums[j] = temp
            }
        }
        i++
        nums[end] = nums[i]
        nums[i] = pivot
        return i
    }
}
