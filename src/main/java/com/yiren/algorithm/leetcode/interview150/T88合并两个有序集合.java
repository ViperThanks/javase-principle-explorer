package com.yiren.algorithm.leetcode.interview150;

/**
 * desc: 初见感觉双指针，应该会有扫尾问题
 *
 * @author Viper Thanks
 * @since 14/4/2024
 */
public class T88合并两个有序集合 {

}

class T88Solution{
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[n + m];
        int k = 0;
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                res[k++] = nums1[i];
                i++;
            }else {
                res[k++] = nums2[j];
                j++;
            }
        }
        while (i < m) {
            res[k++] = nums1[i++];
        }
        while (j < n) {
            res[k++] = nums2[j++];
        }
        System.arraycopy(res, 0, nums1, 0, nums1.length);
    }
}
