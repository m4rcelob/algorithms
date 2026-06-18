package com.redeprivada.sorting;

public class HeapSort implements Sortable {

    @Override
    public void sort(int[] nums) {
        int heapsize = nums.length;
        buildmaxheap(nums, heapsize);
        for (int i = heapsize - 1; i >= 1; --i) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            heapsize--;
            maxheapify(nums, heapsize, 0);
        }
    }

    private void maxheapify(int[] nums, int heapsize, int i) {
        int l = (i + 1) * 2 - 1;
        int r = (i + 1) * 2;
        int largest = i;
        if (l < heapsize && nums[l] > nums[i])
            largest = l;
        if (r < heapsize && nums[r] > nums[largest])
            largest = r;
        if (largest != i) {
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;
            maxheapify(nums, heapsize, largest);
        }
    }

    private void buildmaxheap(int[] nums, int heapsize) {
        for (int i = heapsize / 2; i >= 0; --i) {
            maxheapify(nums, heapsize, i);
        }
    }
}
