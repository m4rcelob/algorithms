package com.redeprivada.sorting

class InsertionSort : Sortable {
    override fun sort(nums: IntArray) {
        for (i in 1..< nums.size) {
            val key = nums[i]
            var j = i - 1
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j]
                j -= 1
            }
            nums[j + 1] = key
        }
    }
}