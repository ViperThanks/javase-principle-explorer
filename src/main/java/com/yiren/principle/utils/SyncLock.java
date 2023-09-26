package com.yiren.principle.utils;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 13/9/2023
 */
public final class SyncLock {
  private final String name;

  private final Integer id;

  public SyncLock(Integer id, String name) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Integer getId() {
    return id;
  }

  @Override
  public String toString() {
    return "SyncLock{" +
      "name='" + name + '\'' +
      ", id=" + id +
      '}';
  }
}
