package com.yiren.algorithm.leetcode.interview150;

import com.yiren.algorithm.utils.AlgoUtils;
import com.yiren.utils.CommonUtils;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 8/5/2024
 */
public class T169多数元素 {
    public static void main(String[] args) {
        int[] ints = {2,2,1,1,1,2,2,};
        int i = new T169Solution().majorityElement(ints);
        CommonUtils.println(ints);
        CommonUtils.println(i);
    }
     static class T169Solution{
        public int majorityElement(int[] nums) {
            int k = 0;
            while (k < nums.length) {
                if (nums[k] < 0) {
                    k++;
                    continue;
                }
                int value = nums[nums[k] - 1];
                if (value > 0) {
                    swap(nums, nums[k] - 1, k);
                    nums[nums[k] - 1] = -1;
                }else {
                    nums[nums[k] - 1] -= 1;
                    k++;
                }
            }
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (num < 0 && num * (-1) > nums.length / 2) {
                    return i + 1;
                }
            }
            return 0;
        }

        private void swap(int[] a, int x, int y) {
            AlgoUtils.swap(a, x, y);
        }
    }
}
