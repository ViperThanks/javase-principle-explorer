package com.yiren.algorithm.leetcode.interview150;

import com.yiren.utils.CommonUtils;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 8/5/2024
 */
public class T26删除有序数组中的重复项 {
    public static void main(String[] args) {
        int[] ints = {0,0,1,1,1,2,2,3,3,4};
        int i = new T26Solution().removeDuplicates(ints);
        CommonUtils.println(ints);
        CommonUtils.println(i);
    }

}

class T26Solution {
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) nums[++j] = nums[i];
        }
        return j + 1;
    }
}
