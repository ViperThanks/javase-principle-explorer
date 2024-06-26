package com.yiren.algorithm.leetcode.daily;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 14/4/2024
 */

public class T705设计哈希集合 {
    public static void main(String[] args) {
        MyHashTable myHashTable = new MyHashTable();
        myHashTable.add(1);
    }
}
class MyHashTable{
    private static final Integer N = 16384;
    private final Node[] elementData;

    public MyHashTable() {
        elementData = new Node[N];
    }


    public int hash(int key){
        //扰动函数
        key ^= key >>> 16;
        return key & (N - 1);
    }

    public void add(int key) {
        if (contains(key)) {
            return;
        }
        //头插
        int idx = hash(key);
        Node node = elementData[idx];
        if (node == null) {
            elementData[idx] = new Node(key, null);
            return;
        }
        Node newNode = new Node(key, node);
        elementData[idx] = newNode;
    }

    public void remove(int key) {
        int idx = hash(key);
        Node node = elementData[idx];
        if (node == null) {
            return;
        }
        if (node.key == key) {
            elementData[idx] = node.next;
        }
        while (node.next != null) {
            if (node.next.key == key) {
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }
    }

    public boolean contains(int key) {
        int idx = hash(key);
        Node node;
        if ((node = elementData[idx]) == null) {
            return false;
        }
        if (node.key == key) {
            return true;
        }
        while (node.next != null) {
            node = node.next;
            if (node.key == key) {
                return true;
            }
        }
        return false;
    }

    private static final class Node{
        private final int key;
        private Node next;

        public Node(int key, Node next) {
            this.key = key;
            this.next = next;
        }
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */