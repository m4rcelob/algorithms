package com.redeprivada.sorting;

public class MergeSort implements Sortable {

    @Override
    public void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid+1, end);
            merge(nums, start, mid, end);
        }
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int side1len = mid - start + 1;
        int side2len = end - mid;

        int[] side1 = new int[side1len];
        int[] side2 = new int[side2len];

        System.arraycopy(nums, start, side1, 0, side1len);
        System.arraycopy(nums, mid + 1, side2, 0, side2len);

        int pos = start;
        int i = 0;
        int j = 0;
        while (i < side1len && j < side2len) {
            if (side1[i] <= side2[j]) {
                nums[pos++] = side1[i++];
            } else {
                nums[pos++] = side2[j++];
            }
        }
        while (i < side1len)
            nums[pos++] = side1[i++];
        while (j < side2len)
            nums[pos++] = side2[j++];
    }
}
