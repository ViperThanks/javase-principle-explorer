package com.yiren.algorithm.leetcode.hot100;

import com.yiren.algorithm.datastructure.ListNode;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 14/9/2023
 */
public class T21合并两个有序链表 {
  public static void main(String[] args) {
    //new Solution2().mergeTwoLists()
  }

}


class Solution2{
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode sentinel = new ListNode(-1);
    ListNode pre = sentinel;
    while (list1 != null && list2 != null) {
      if (list2.val <= list1.val) {
        pre.next = list2;
        list2 = list2.next;
      }else {
        pre.next = list1;
        list1= list1.next;
      }
      pre = pre.next;
    }

    pre.next = list1 == null ? list2 : list1;

    return sentinel.next;
  }
}
