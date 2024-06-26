package com.yiren.redis;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 8/5/2024
 */
public class SortedSet {

    private static final ConcurrentHashMap<String, SortedSet> storage = new ConcurrentHashMap<>(16);


    private final ConcurrentSkipListMap<Double, String> skipListMap;
    private final Map<String, Double> hashMap;
    private final String uniqueName;

    public SortedSet(final String uniqueName) {
        Preconditions.checkState(!storage.containsKey(uniqueName),uniqueName + " sorted set was existed");
        this.skipListMap = new ConcurrentSkipListMap<>();
        this.hashMap = new ConcurrentHashMap<>();
        this.uniqueName = uniqueName;
        storage.put(uniqueName, this);
    }

    public String getUniqueName(){
        return this.uniqueName;
    }

    private boolean zadd(String key, double score) {
        checkState();
        Double scoreFindByMap = this.hashMap.get(key);
        if (scoreFindByMap != null) {
            this.skipListMap.remove(scoreFindByMap, key);
        }
        this.hashMap.put(key, score);
        this.skipListMap.put(score, key);
        return true;
    }

    private void checkState(){
        if (this.skipListMap.size() != this.hashMap.size())
            throw new ConcurrentModificationException();
    }

    private int zcount(double min, double max) {
        return this.skipListMap.subMap(min, true, max, true).size();
    }

    private static SortedSet getStrictly(final String uniqueName) {
        return Objects.requireNonNull(get(uniqueName), uniqueName + " sorted set is not exist");
    }

    @Override
    public String toString(){
        return toString(this.uniqueName);
    }

    public static String toString(String uniqueName){
        return Objects.toString(getStrictly(uniqueName).hashMap);
    }

    public static String toPrintable(String uniqueName) {
        return toString(uniqueName);
    }

    // static 方法们

    /**
     * 往 sorted set 插入 key 和 value 分数
     * @param key 需要插入的key
     * @param value 需要插入的分数
     * @return 1为成功 0为失败
     */
    public static int zadd(SortedSet sortedSet, String key, Double value) {
        return sortedSet.zadd(key, value) ? 1 : 0;
    }

    public static int zcount(SortedSet sortedSet, double min, double max) {
        return sortedSet.zcount(min, max);
    }

    /**
     * 往名为uniqueName sorted set 插入 key 和 value 分数
     * @param key 需要插入的key
     * @param value 需要插入的分数
     * @return 1为成功 0为失败
     */
    public static int zadd(String uniqueName, String key, Double value) {
        return zadd(getStrictly(uniqueName), key, value);
    }

    public static int zcount(String uniqueName, double min, double max) {
        return zcount(getStrictly(uniqueName), min, max);
    }

    /**
     * 根据unique name获取 sorted set对象
     */
    public static SortedSet get(final String uniqueName) {
        return storage.get(uniqueName);
    }

    /**
     * 创建一个sorted set对象
     */
    public static SortedSet create(final String uniqueName) {
        return new SortedSet(uniqueName);
    }

    /**
     * 创建一个sorted set对象
     * unique name由内部维护
     */
    public static SortedSet create() {
        String uniqueName = UUID.randomUUID().toString();
        return create(uniqueName);
    }
}
