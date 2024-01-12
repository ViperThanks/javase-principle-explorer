package com.yiren.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-11-20
 */
public class LogicDataGenerator<T, R> {

  public static <T, R> LogicDataGenerator<T, R> newGenerator() {
    return new LogicDataGenerator<>();
  }

  private LogicDataGenerator() {
  }

  private List<T> dataList;
  private Function<R, T> newInstanceFunction;
  private Function<T, R> getter;
  private Supplier<Collection<R>> rangeSupplier;

  public LogicDataGenerator<T, R> dataList(List<T> dataList) {
    this.dataList = dataList;
    return this;
  }

  public LogicDataGenerator<T, R> newInstanceFunction(Function<R, T> newInstanceFunction) {
    this.newInstanceFunction = newInstanceFunction;
    return this;
  }

  public LogicDataGenerator<T, R> getter(Function<T, R> getter) {
    this.getter = getter;
    return this;
  }

  public LogicDataGenerator<T, R> rangeSupplier(Supplier<Collection<R>> rangeSupplier) {
    this.rangeSupplier = rangeSupplier;
    return this;
  }

  public List<T> generate() {
    if (dataList == null || newInstanceFunction == null || getter == null || rangeSupplier == null) {
      throw new IllegalStateException("DataList, newInstanceFunction, getter, and rangeSupplier must be provided");
    }

    Set<R> existingData = dataList.stream().map(getter).collect(Collectors.toSet());
    Set<R> range = new HashSet<>(rangeSupplier.get());

    range.removeAll(existingData);  // 移除已存在的数据，保留缺失的数据
    List<T> newItems = range.stream()
        .map(newInstanceFunction)
        .collect(Collectors.toList());

    dataList.addAll(newItems);  // 将新生成的数据项添加到原始数据列表中
    return newItems;  // 返回新生成的数据项
  }
}
