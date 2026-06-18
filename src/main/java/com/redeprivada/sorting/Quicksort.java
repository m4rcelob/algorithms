package com.redeprivada.sorting;

public class Quicksort implements Sortable {
    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int begin, int end) {
        if (begin < end) {
            int pivot = partition(nums, begin, end);
            sort(nums, begin, pivot - 1);
            sort(nums, pivot + 1, end);
        }
    }

    private int partition (int[] nums, int begin, int end) {
        int i = begin - 1;
        int pivot = nums[end];
        for (int j = begin; j < end; j++) {
            if (nums[j] < pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        i++;
        nums[end] = nums[i];
        nums[i] = pivot;
        return i;
    }
}
