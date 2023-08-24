package com.yiren.utils;

import lombok.Getter;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 28/3/2023 下午 10:39
 * desc     : 性能测试的结果类,包含花费的时间和花费的内存,基于JVM的内存管理器 {@linkplain MemoryMXBean}
 * 和 {@linkplain System#nanoTime()}()方法
 */

public final class Performance {

  /**
   * 测试代码的时间
   */
  private Long timeUsed;
  private Double millisecond;
  private Double second;
  private Long heepMemoryBefore;
  private Long nonHeapMemoryBefore;
  private Long heepMemoryAfter;
  private Long nonHeapMemoryAfter;
  private Long heepMemoryUsed;
  private Long nonHeapMemoryUsed;

  private Performance() {
  }

  private Performance(Long timeUsed) {
    this.timeUsed = timeUsed;
    this.millisecond = timeUsed / 1000000.0;
    this.second = timeUsed / 1000000000.0;
  }

  private Performance(Long timeUsed, Long heepMemoryUsed, Long nonHeapMemoryUsed) {
    this.timeUsed = timeUsed;
    this.millisecond = timeUsed / 1000000.0;
    this.second = timeUsed / 1000000000.0;
    this.heepMemoryUsed = heepMemoryUsed;
    this.nonHeapMemoryUsed = nonHeapMemoryUsed;
  }

  /**
   * 测试代码的性能,包含花费的时间和花费的内存
   *
   * @param code 要测试的代码
   * @return 性能测试结果
   */
  public static Performance run(Runnable code) {
    return run0(code);
  }

  /**
   * 深度测试代码的性能,包含花费的时间和花费的内存, isDeeply开始和结束的所有(heep & non-heep)内存情况
   *
   * @param code 要测试的代码
   * @return 性能测试结果
   */
  private synchronized static Performance run0(Runnable code) {
    MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    MemoryUsage heapBefore = memoryMXBean.getHeapMemoryUsage();
    MemoryUsage nonHeapBefore = memoryMXBean.getNonHeapMemoryUsage();
    Long start = System.nanoTime();

    code.run();

    Long end = System.nanoTime();
    MemoryUsage heapAfter = memoryMXBean.getHeapMemoryUsage();
    MemoryUsage nonHeapAfter = memoryMXBean.getNonHeapMemoryUsage();
    Performance p = new Performance();
    //注入属性
    p.timeUsed = end - start;
    p.millisecond = p.timeUsed / 1000000.0;
    p.second = p.timeUsed / 1000000000.0;
    p.heepMemoryBefore = heapBefore.getUsed();
    p.nonHeapMemoryBefore = nonHeapBefore.getUsed();
    p.heepMemoryAfter = heapAfter.getUsed();
    p.nonHeapMemoryAfter = nonHeapAfter.getUsed();
    p.heepMemoryUsed = p.heepMemoryAfter - p.heepMemoryBefore;
    p.nonHeapMemoryUsed = p.nonHeapMemoryAfter - p.nonHeapMemoryBefore;
    return p;
  }

  /**
   * 打印测试结果,调用toString0
   */
  public void toPrintable() {
    System.out.println(string0(false));
  }

  /**
   * 打印测试结果,调用toString0
   */
  public void toDetailPrintable() {
    System.out.println(string0(true));
  }

  /**
   * 生成测试结果的字符串 代码非常冗余 但是很直观. 偏yaml风格
   *
   * @return 测试结果的字符串
   */
  @Override
  public String toString() {
    return string0(false);
  }

  /**
   * 生成测试结果的字符串 代码非常冗余 但是很直观. 偏yaml风格
   *
   * @return 测试结果的字符串
   */
  private String string0(boolean detail) {
    final StringBuilder sb = new StringBuilder();
    sb.append("test result : " + "\n");
    sb.append("     normal data : " + "\n");
    sb.append("         -used time -> ").append(millisecond).append(" ms").append("\n");
    if (heepMemoryUsed != null && nonHeapMemoryUsed != null) {
      sb.append("         -used heap memory -> ")
        .append(heepMemoryUsed)
        .append(" byte")
        .append(" or ")
        .append(MemoryUtil.format(heepMemoryUsed))
        .append("\n");
      sb.append("         -used non-heap memory -> ")
        .append(nonHeapMemoryUsed)
        .append(" byte")
        .append(" or ")
        .append(MemoryUtil.format(nonHeapMemoryUsed))
        .append("\n");
    }
    // is deeply
    if (detail) {
      sb.append("     deep data : " + "\n");
      sb.append("         -heap memory before -> ")
        .append(heepMemoryBefore)
        .append(" byte").append(" or ")
        .append(MemoryUtil.format(heepMemoryBefore))
        .append("\n");
      sb.append("         -heap memory after -> ")
        .append(heepMemoryAfter)
        .append(" byte")
        .append(" or ")
        .append(MemoryUtil.format(heepMemoryAfter))
        .append("\n");
      sb.append("         -non-heap memory before -> ")
        .append(nonHeapMemoryBefore)
        .append(" byte")
        .append(" or ")
        .append(MemoryUtil.format(nonHeapMemoryBefore))
        .append("\n");
      sb.append("         -non-heap memory after -> ")
        .append(nonHeapMemoryAfter)
        .append(" byte")
        .append(" or ")
        .append(MemoryUtil.format(nonHeapMemoryAfter))
        .append("\n");
    }
    return sb.toString();
  }
}
