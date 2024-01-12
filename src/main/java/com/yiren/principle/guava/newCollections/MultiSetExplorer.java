package com.yiren.principle.guava.newCollections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 2024-1-11
 */
public class MultiSetExplorer extends BaseExplorer {

  public static void main(String[] args) {
    Executor.executeExplorer(MultiSetExplorer.class);
  }

  private static final String filePath = "D:\\Projects\\ja-project\\javase-principle-explorer\\src\\main\\resources\\testdata\\words.txt";

  private static final File FILE = new File(filePath);
  @Override
  public void explore() throws Exception {
    Multiset<Character> words = HashMultiset.create();
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
      char[] buff = new char[1024];
      int len;
      while ((len = reader.read(buff)) != -1) {
        for (int i = 0; i < len; i++) {
          words.add(buff[i]);
        }
      }
    }
    log.info(String.valueOf(words));
    log.info("{}", words.count(' '));

  }
}
