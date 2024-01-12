package com.yiren.tmp;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseTest;
import com.yiren.core.expand.BaseTestExplorer;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import sun.misc.Unsafe;

/**
 * desc:
 *
 * @author weilin
 * @since 2024-1-8
 */
public class Test2 extends BaseTestExplorer {

  public static void main(String[] args) {
    Executor.executeExplorer(Test2.class);
  }

  @Override
  public void explore() throws Exception {
    Multiset<@Nullable Character> words = HashMultiset.create();
    byte[] byteArray = Files.toByteArray(
        new File("D:\\Projects\\ja-project\\javase-principle-explorer\\src\\main\\resources\\testdata\\words.txt"));
    for (byte b : byteArray) {
      words.add((char) b);
    }
    //merchantStore key merchantStoreId,intervalNO,positionId, values()
    LOGGER.info(String.valueOf(words));
    LOGGER.info(String.valueOf(words.count('a')));
  }
}
