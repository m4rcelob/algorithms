package com.m4rcelob.sorting

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

    private fun maxheapify(nums: IntArray, heapsize: Int, nodeIndex: Int) {
        val l = (nodeIndex + 1) * 2 - 1
        val r = (nodeIndex + 1) * 2
        var largest = nodeIndex
        if (l < heapsize && nums[l] > nums[nodeIndex]) largest = l
        if (r < heapsize && nums[r] > nums[largest]) largest = r
        if (largest != nodeIndex) {
            val temp = nums[nodeIndex]
            nums[nodeIndex] = nums[largest]
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
