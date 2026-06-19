package com.redeprivada.sorting

class QuickSort : Sortable {
    override fun sort(nums: IntArray) {
        sort(nums, 0, nums.size - 1)
    }

    private fun sort(nums: IntArray, begin: Int, end: Int) {
        if (begin < end) {
            val pivot = partition(nums, begin, end)
            sort(nums, begin, pivot - 1)
            sort(nums, pivot + 1, end)
        }
    }

    private fun partition(nums: IntArray, begin: Int, end: Int): Int {
        var i = begin - 1
        val pivot = nums[end]
        for (j in begin..<end) {
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
