package com.yiren.principle.javase.零拷贝技术;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * <h2>
 * desc    :
 * </h2>
 *
 * @author : weilin
 * @since : 31/8/2023
 */
public class ZeroCopyExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(ZeroCopyExplorer.class);


  public static void main(String[] args) {
    Executor.executeExplorer(ZeroCopyExplorer.class);
  }


  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    normalCopy();
    zeroCopy();
  }


  public void normalCopy() throws Exception {

    long start = System.currentTimeMillis();

    //core  code
    FileInputStream inputStream = new FileInputStream("d:/test_data/test_data.7z");
    FileOutputStream outputStream = new FileOutputStream("d:/test_data/test_data1.7z");
    byte[] buffer = new byte[1 << 12];
    while (inputStream.read(buffer) >= 0) {
      outputStream.write(buffer);
    }
    outputStream.flush();

    long end = System.currentTimeMillis();
    inputStream.close();
    outputStream.close();
    log.info("normalCopy execute time {} ms", end - start);
  }

  public void zeroCopy() throws Exception {

    long start = System.currentTimeMillis();

    //core  code
    File from = new File("d:/test_data/test_data.7z");
    File to = new File("d:/test_data/test_data1.7z");


    FileChannel fromChannel = new RandomAccessFile(from, "r").getChannel();
    FileChannel toChannel = new RandomAccessFile(to, "rw").getChannel();
    fromChannel.transferTo(0,from.length(),toChannel);


    long end = System.currentTimeMillis();

    log.info("zeroCopy execute time {} ms", end - start);
  }
}
