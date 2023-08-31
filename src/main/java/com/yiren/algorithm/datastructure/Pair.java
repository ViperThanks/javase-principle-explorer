package com.yiren.algorithm.datastructure;

/**
 * <p>
 * desc    : 二元组
 * </p>
 *
 * @author weilin
 * @since 30/8/2023
 */
public class Pair<K, V> {
  public K key;
  public V value;

  public Pair() {
  }

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "(" + key + "," + value + ")";
  }

}
