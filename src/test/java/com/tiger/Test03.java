package com.tiger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test03 {


    public static void main(String[] args) {

        int nums[] = {0,0,0};
        new Test03().threeSum(nums);
    }

    public String longestCommonPrefix(String[] strs) {
        int size = strs.length;
        if (size == 0) {
            return "";
        }
        if (size <= 1) {
            return strs[0];
        }
        // 找最长字符串
        String maxStr = strs[0];
        for (int i = 1; i < size; i++) {
            if (maxStr.length() < strs[i].length()) {
                maxStr = strs[i];
            }
        }
        String preFix = "";
        for (int i = 1; i < maxStr.length() + 1; i++) {
            String tmpPreFix = maxStr.substring(0, i);
            int j = 0;
            for (; j < size; j++) {
                if (!strs[j].startsWith(tmpPreFix)) {
                    break;
                }
            }
            if (j == size) {
                preFix = tmpPreFix;
            }
        }
        return preFix;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int size = nums.length;
        if (size <= 2) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < size - 2; i++) {
            for (int j = i + 1; j < size - 1; j++) {
                if (nums[i] + nums[j] + nums[j + 1] > 0) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[size - 1] < 0) {
                    continue;
                }
                if(nums[i] + nums[j] + nums[j+1] ==0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[j+1]);
                    if (!result.contains(list)) {
                        result.add(list);
                    }
                    continue;
                }

                if(nums[i]+nums[j]+nums[size-1]==0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[size-1]);
                    if (!result.contains(list)) {
                        result.add(list);
                    }
                    continue;
                }

                int sum = nums[i] + nums[j];
                int search = Arrays.binarySearch(nums, -sum);
                if(search > j){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[search]);
                    if (!result.contains(list)) {
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }


}
