package com.yiren.algorithm.datastructure;

/**
 * desc  : 链表节点
 * copy by leetcode
 */
public class ListNode {
    public
    int val;
    public
    ListNode next;


    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 将链表转换为字符串
     * 效果如下:
     * null -> 1 -> 2 -> 3 -> 4 -> 5 -> null
     */
    @Override
    public String toString() {
        ListNode current = this;
        //null ->
        StringBuilder sb = new StringBuilder("null -> ");
        while (current != null) {
            sb.append(current.val).append(" -> ");
            current = current.next;
        }
        //null -> ... -> null
        sb.append("null");
        return sb.toString();
    }

}