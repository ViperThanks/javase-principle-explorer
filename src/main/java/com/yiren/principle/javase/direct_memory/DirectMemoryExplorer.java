package com.yiren.principle.javase.direct_memory;

import com.yiren.core.Explorer;
import com.yiren.utils.Memory;
import com.yiren.utils.MemoryUtil;
import org.openjdk.jol.util.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * desc  :
 * author: weilin
 * date  : 26/7/2023
 */
public class DirectMemoryExplorer implements Explorer {

  static final String FROM = "e://from.txt";
  static final String TO = "e://to.txt";


  @Override
  public void explore() {

  }

  private static void io() {
    long start = System.currentTimeMillis();
    try (FileInputStream fis = new FileInputStream(FROM);
         FileOutputStream to = new FileOutputStream(TO)
    ) {

      byte[] bytes = MemoryUtil.getBytes(1, Memory.MB);
      int len;
      while ((len = fis.read(bytes)) != -1) {
        to.write(bytes, 0, len);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void nio(){

  }
}
