package com.yiren.tmp;

import static com.yiren.algorithm.utils.Python.range;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yiren.algorithm.AlgorithmTemplate;
import com.yiren.algorithm.array.ArraySortAlgorithmTemplate;
import com.yiren.algorithm.array.QuickSort;
import com.yiren.algorithm.datastructure.Pair;
import com.yiren.algorithm.utils.AlgoUtils;
import com.yiren.algorithm.utils.Python;
import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.core.ExplorerTestable;
import com.yiren.entity.Employee;
import com.yiren.entity.User;
import com.yiren.principle.javase.零拷贝技术.ZeroCopyExplorer;
import com.yiren.utils.LogicDataGenerator;
import com.yiren.utils.Performance;
import com.yiren.utils.Printer;
import com.yiren.utils.StopWatch;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * desc  : author: weilin date  : 17/7/2023
 */
public class JavaTest implements ExplorerTestable {

  private static final Logger LOGGER = LoggerFactory.getLogger(JavaTest.class);


  public static void main(String[] args) {


  }

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {

  }


  @Data
  @Accessors(chain = true)
  private static final class EasyVo {

    Employee employee;
    List<User> userList;
  }

  @Test
  public void test() {
    ArraySortAlgorithmTemplate template = new QuickSort();
    int[] a = AlgoUtils.randomIntArr(100_000_000);
    int[] b = a.clone();
    System.out.println(a == b);
    System.out.println((StopWatch.calculateMilliseconds(() -> template.sort(b, 0, b.length - 1))));
    System.out.println(ArrayUtils.isSorted(b));
    System.out.println(StopWatch.calculateMilliseconds(() -> Arrays.sort(b)));
    System.out.println(ArrayUtils.isSorted(b));
  }

  String toBin(double num) {
    StringBuilder sb = new StringBuilder();
    sb.append("0.");
    while (num > 0) {
      num *= 2;
      if (num >= 1) {
        sb.append(1);
        num -= 1;
      } else {
        sb.append(0);
      }
      if (sb.length() > 32) {
        return "ERROR:" + sb.toString();
      }
    }
    return sb.toString();
  }

  @Test
  public void test1() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] args = reader.readLine().split(" ");
    int[] arr = new int[args.length - 1];
    for (int i = 0; i < args.length - 1; i++) {
      arr[i] = Integer.parseInt(args[i]);
    }
    int k = Integer.parseInt(args[args.length - 1]);
  }

  @Test
  public void test2() throws IOException {
    HashMap<@Nullable String, @Nullable User> map = Maps.newHashMap();
    map.computeIfAbsent("1", this::getById);
  }

  public User getById(String id) {
    return getById(NumberUtils.isParsable(id) ? Long.valueOf(id) : -1L);
  }

  public User getById(long id) {
    return new User().setId(id);
  }

  @Test
  public void teszt11() {
    List<Pair<String, Function<String, String>>> list = Lists.newArrayList(new Pair<>("java", s -> s + 1),
        new Pair<>("guava", s -> s + 2));
    StringBuilder sb = new StringBuilder();
    for (Pair<String, Function<String, String>> pair : list) {
      sb.append(pair.getKey()).append(':').append(pair.getValue().apply(pair.getKey()));
    }
    System.out.println("sb = " + sb);
  }


  private String test(String srcRealName) {
    return Optional.ofNullable(srcRealName)
        .filter(StringUtils::isNotBlank)
        .orElse("全部");
  }

}

