package com.m4rcelob.sorting

class MergeSort : Sortable {
    override fun sort(nums: IntArray) {
        mergeSort(nums, 0, nums.size - 1)
    }

    private fun mergeSort(nums: IntArray, start: Int, end: Int) {
        if (start < end) {
            val mid = (start + end) / 2
            mergeSort(nums, start, mid)
            mergeSort(nums, mid + 1, end)
            merge(nums, start, mid, end)
        }
    }

    private fun merge(nums: IntArray, start: Int, mid: Int, end: Int) {
        val side1len = mid - start + 1
        val side2len = end - mid

        val side1 = IntArray(side1len)
        val side2 = IntArray(side2len)

        System.arraycopy(nums, start, side1, 0, side1len)
        System.arraycopy(nums, mid + 1, side2, 0, side2len)

        var pos = start
        var i = 0
        var j = 0
        while (i < side1len && j < side2len) {
            if (side1[i] <= side2[j]) {
                nums[pos++] = side1[i++]
            } else {
                nums[pos++] = side2[j++]
            }
        }
        while (i < side1len) nums[pos++] = side1[i++]
        while (j < side2len) nums[pos++] = side2[j++]
    }
}
