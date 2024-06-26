package com.yiren.algorithm.leetcode.daily;

import com.yiren.utils.CommonUtils;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 28/4/2024
 */
public class T283移动零 {
    public static void main(String[] args) {
        int[] srcNums = CommonUtils.getRandomIntArr(15);
        for (int i = 0; i < 3; i++) {
            int randomInt = CommonUtils.getRandomInt(srcNums.length);
            srcNums[randomInt] = 0;
        }
        CommonUtils.println(srcNums);
        new Solution().moveZeroes(srcNums);
        CommonUtils.println(srcNums);
    }
}


class Solution{
    public void moveZeroes(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == 0) {
                int j = findNextNotZeroIndex(nums, i, size);
                if (j == size || j == -1) {
                    return;
                }
                swap(nums, i, j);
            }
        }
    }

    private int findNextNotZeroIndex(int[] nums, int startIndex, int maxLength) {
        if (startIndex == maxLength) {
            return maxLength;
        }
        for (int i = startIndex + 1; i < maxLength; i++) if (nums[i] != 0) return i;
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int x = nums[i];
        nums[i] = nums[j];
        nums[j] = x;
    }
}
