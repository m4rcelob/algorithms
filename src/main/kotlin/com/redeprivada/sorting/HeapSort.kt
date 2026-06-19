package com.redeprivada.sorting

class HeapSort : Sortable {
    override fun sort(nums: IntArray) {
        var heapsize = nums.size
        buildmaxheap(nums, heapsize)
        for (i in heapsize - 1 downTo 1) {
            val temp = nums[0]
            nums[0] = nums[i]
            nums[i] = temp
            heapsize--
            maxheapify(nums, heapsize, 0)
        }
    }

    private fun maxheapify(nums: IntArray, heapsize: Int, i: Int) {
        val l = (i + 1) * 2 - 1
        val r = (i + 1) * 2
        var largest = i
        if (l < heapsize && nums[l] > nums[i]) largest = l
        if (r < heapsize && nums[r] > nums[largest]) largest = r
        if (largest != i) {
            val temp = nums[i]
            nums[i] = nums[largest]
            nums[largest] = temp
            maxheapify(nums, heapsize, largest)
        }
    }

    private fun buildmaxheap(nums: IntArray, heapsize: Int) {
        for (i in heapsize / 2 downTo 0) {
            maxheapify(nums, heapsize, i)
        }
    }
}
