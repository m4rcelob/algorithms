package com.redeprivada.sorting;

public class Quicksort {
    private Quicksort() {
        /* This utility class should not be instantiated */
    }

    public static void quicksort(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
    }

    private static void quicksort(int[] nums, int begin, int end) {
        if (begin < end) {
            int pivot = partition(nums, begin, end);
            quicksort(nums, begin, pivot - 1);
            quicksort(nums, pivot + 1, end);
        }
    }

    private static int partition (int[] nums, int begin, int end) {
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
