package com.redeprivada.sorting;

public class Insertionsort implements Sortable {
    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;
            while(j >= 0 && nums[j] > key) {
                nums[j+1] = nums[j];
                j = j - 1;
            }
            nums[j + 1] = key;
        }
    }
}