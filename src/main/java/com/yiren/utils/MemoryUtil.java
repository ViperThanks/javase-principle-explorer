package com.yiren.utils;


import java.text.DecimalFormat;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 6/4/2023 下午 5:31
 * desc     :
 */

public class MemoryUtil {

  public static String format(long bytes) {
    DecimalFormat format = new DecimalFormat("###.###");
    if (bytes >= Memory.TB.toB())
      return format.format((double) bytes / Memory.TB.toB()) + " TB";
    else if (bytes >= Memory.GB.toB())
      return format.format((double) bytes / Memory.GB.toB()) + " GB";
    else if (bytes >= Memory.MB.toB())
      return format.format((double) bytes / Memory.MB.toB()) + " MB";
    else if (bytes >= Memory.KB.toB())
      return format.format((double) bytes / Memory.KB.toB()) + " KB";
    else
      return bytes + " B";
    //改写成switch语句

  }

  /**
   * 获取指定大小的字节数组默认为MB
   *
   * @param mbSizes 单位为B
   * @return 字节数组
   */
  public static byte[] getBytes(int mbSizes) {
    return getBytes(mbSizes, Memory.MB);
  }

  /**
   * 获取指定大小的字节数组,不能超过jvm指定的数组长度最大值 {@link  Integer#MAX_VALUE}
   *
   * @param size   单位为B
   * @param memory 单位
   * @return 字节数组
   */
  public static byte[] getBytes(int size, Memory memory) {
    long tmp = size * memory.toB();
    if (tmp > Integer.MAX_VALUE)
      throw new IllegalArgumentException("too big");
    return new byte[(int) (memory.toB() * size)];
  }


}
