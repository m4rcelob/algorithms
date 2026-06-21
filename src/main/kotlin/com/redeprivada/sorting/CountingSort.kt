package com.redeprivada.sorting

class CountingSort(val k: Int): Sortable {
    override fun sort(nums: IntArray) {
        val n = nums.size
        val count = IntArray(k)
        val sorted = IntArray(n)
        nums.forEach {
            count[it]++
        }
        for (j in 1 ..< k) {
            count[j] += count[j-1]
        }
        for (i in n-1 downTo 0) {
            sorted[count[nums[i]]-1] = nums[i]
            count[nums[i]]--
        }
        sorted.forEachIndexed { i, value ->
            nums[i] = value
        }
    }
}