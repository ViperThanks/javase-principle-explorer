package com.yiren.tmp;


import static java.math.RoundingMode.UNNECESSARY;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;
import com.google.common.collect.BiMap;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.Collections2;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.google.common.collect.MoreCollectors;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.io.BaseEncoding;
import com.google.common.io.MoreFiles;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.Ints;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.utils.CommonUtils;
import com.yiren.utils.CommonUtils.RandomHolder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Observer;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-12-27
 */
public class test1 extends BaseExplorer {

  private static final Cache<String, Long> cache = CacheBuilder.newBuilder()
      .maximumWeight(15)
      .weigher((Weigher<String, Long>) (key, value) -> key.length())
      .expireAfterWrite(1, TimeUnit.SECONDS)
      .build();

  private static final String[] emoji = new String[]{"[撇嘴][撇嘴][撇嘴][撇嘴][撇嘴][撇嘴]\n"
      + "[难过][撇嘴][难过][撇嘴][撇嘴][撇嘴]\n"
      + "[难过][撇嘴][难过][难过][撇嘴][撇嘴]\n"
      + "[难过][撇嘴][撇嘴][难过][撇嘴][难过]\n"
      + "[难过][撇嘴][撇嘴][难过][撇嘴][难过]"};

  public static void main(String[] args) {

    /*List<String> list = Stream.generate(() -> getRandomString(5)).limit(100).toList();
    String join = Joiner.on("沸杨杨").join(list);*/
//    System.out.println(join);
    /*Integer[] randomArr = getRandomArr(10, RandomHolder.THREAD_LOCAL_RANDOM::nextInt);
    System.out.println(Arrays.toString(randomArr));*/
    /*HashMultiset<@Nullable Object> objects = HashMultiset.create();
    objects.add(1);
    objects.add(1);
    objects.add(2);
    System.out.println(objects);
    */
    List<String> list = Stream.generate(RandomHolder.THREAD_LOCAL_RANDOM::nextLong).limit(100)
        .flatMap(num -> Splitter.on(',').splitToStream(num.toString())).collect(Collectors.toList());
    ArrayUtils.reverse(emoji);
    System.out.println(Arrays.toString(emoji));

  }

  @RequiredArgsConstructor
  private static final class A{
    private final String name;
  }

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    ArrayList<String> strings = Lists.newArrayList("yxw", "hs", "ys");
    String join = Joiner.on("-沸杨杨-").join(strings);
    log.info(join);
    List<String> strings1 = Splitter.on("-沸杨杨-").splitToList(join);
    log.info(String.valueOf(strings1));
    log.info("{}", Objects.equals(strings1, strings));
  }

  private static final class EventListener {

    @Subscribe
    public void listenInteger(Integer parm) {
      System.out.println(parm + parm.getClass().getTypeName());
    }

    @Subscribe
    public void listenStr(String parm) {
      System.out.println(parm + parm.getClass().getTypeName());
    }
  }


}
