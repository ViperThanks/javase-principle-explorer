package com.yiren.utils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.yiren.enums.IndexDesc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * desc: 实现了{@link IndexDesc}接口的枚举工具类
 *
 * @author Viper Thanks
 * @since 28/6/2024
 */
public final class IndexDescEnumUtils {

    private static final Logger log = LoggerFactory.getLogger(IndexDescEnumUtils.class);


    private IndexDescEnumUtils() {
    }

    /**
     * 索引查找器，基于for循环实现，O（n）时间复杂度，但是一般枚举不多，n很小
     */
    public static final class EnumIndexLoopUpFinder {
        private EnumIndexLoopUpFinder() {
        }

        public static @Nullable <T extends Enum<T> & IndexDesc> T findByIndexOrNull(T[] values, int index) {
            for (T value : values) if (value.getIndex() == index) return value;
            return null;
        }

        public static @Nullable <T extends Enum<T> & IndexDesc> T findByIndexOrNull(Class<T> clazz, int index) {
            return findByIndexOrNull(clazz.getEnumConstants(), index);
        }

        public static @Nullable <T extends Enum<T> & IndexDesc, R> T findByCustomOrNull(Class<T> clazz, Function<T, R> mapping, R expectValue) {
            return findByCustomOrNull(clazz.getEnumConstants(), mapping, expectValue);
        }

        public static <T extends Enum<T> & IndexDesc, R> @Nullable T findByCustomOrNull(T[] values, Function<T, R> mapping, R expectValue) {
            for (T value : values) if (Objects.equals(mapping.apply(value), expectValue)) return value;
            return null;
        }

        public static <T extends Enum<T> & IndexDesc, R> @Nullable T findByCustomOrNull(Class<T> clazz, Predicate<T> predicate) {
            return findByCustomOrNull(clazz.getEnumConstants(), predicate);
        }

        public static <T extends Enum<T> & IndexDesc> T findByCustomOrNull(T[] values, Predicate<T> predicate) {
            for (T value : values) if (predicate.apply(value)) return value;
            return null;
        }
    }

    /**
     * 枚举map生成器，用于生成枚举map，然后通过map查找，O（1）时间复杂度，空间换时间
     */
    public static final class EnumMapGenerator {
        private EnumMapGenerator() {
        }

        /**
         * 通过实体数组获取key:index value:实体 的map
         *
         * @param values 枚举实体数组
         * @param <T>    泛型
         * @return 返回的map不可变！
         */
        public static <T extends Enum<T> & IndexDesc> Map<Integer, T> getIndexEntityMap(T[] values) {
            return Maps.uniqueIndex(Arrays.asList(values), IndexDesc::getIndex);
        }

        /**
         * 通过class获取key:index value:实体 的map
         *
         * @param clazz 枚举class
         * @param <T>   泛型
         * @return 返回的map不可变！
         */
        public static <T extends Enum<T> & IndexDesc> Map<Integer, T> getIndexEntityMap(Class<T> clazz) {
            return getIndexEntityMap(clazz.getEnumConstants());
        }

        /**
         * 通过class获取key:index value:desc
         *
         * @param clazz 枚举class
         * @param <T>   泛型
         * @return 返回的map可变
         */
        public static <T extends Enum<T> & IndexDesc> Map<Integer, String> getIndexDescMap(Class<T> clazz) {
            T[] enumConstants = clazz.getEnumConstants();
            return Arrays.stream(enumConstants).collect(Collectors.toMap(
                    IndexDesc::getIndex,
                    IndexDesc::getDesc,
                    (first, second) -> first,
                    () -> Maps.newHashMapWithExpectedSize(enumConstants.length)
            ));
        }

    }

    /**
     * 枚举map缓存实体，缓存枚举类的对应的map，避免频繁的生成map
     */
    private static final class EnumIndexMapFinderCached {
        private EnumIndexMapFinderCached() {
        }

        private static final com.google.common.cache.Cache<Class<? extends IndexDesc>, Map<Integer, ? extends IndexDesc>> CACHE =
                CacheBuilder.newBuilder()
                        .weakKeys()
                        .weakValues()
                        .maximumSize(100)
                        .build();

    }

    /**
     * 枚举map缓存实体，缓存枚举类的对应的map，避免频繁的生成map
     */
    private static final class EnumIndexMapFinderTimesCached {
        private EnumIndexMapFinderTimesCached() {
        }

        private static final int CACHE_THRESHOLD = 20;
        private static final long TIME_WINDOW = 1000L;

        private static final Cache<Class<? extends IndexDesc>, Integer> CACHE =
                CacheBuilder.newBuilder()
                        .weakKeys()
                        .weakValues()
                        .maximumSize(100)
                        .expireAfterAccess(TIME_WINDOW, TimeUnit.MILLISECONDS)
                        .build();
    }

    /**
     * 枚举map查找器，基于双层map实现，O（1）时间复杂度，空间换时间
     */
    public static final class EnumIndexMapFinder {
        private EnumIndexMapFinder() {
        }

        @SuppressWarnings({"unchecked"})
        public static <T extends Enum<T> & IndexDesc> @Nullable T findByIndexOrNull(Class<T> clazz, int index, boolean useCache) {
            Map<Integer, ? extends IndexDesc> cache = EnumIndexMapFinderCached.CACHE.getIfPresent(clazz);
            if (cache != null) {
                return (T) cache.get(index);
            }
            if (useCache) {
                Map<Integer, T> indexEntityMap = EnumMapGenerator.getIndexEntityMap(clazz);
                EnumIndexMapFinderCached.CACHE.put(clazz, indexEntityMap);
                return indexEntityMap.get(index);
            }
            return EnumMapGenerator.getIndexEntityMap(clazz).get(index);
        }

    }

    /**
     * 枚举查找门面，当未指定强制使用缓存的情况下
     * <br>
     * 只有枚举在{@link EnumIndexMapFinderTimesCached#TIME_WINDOW}毫秒内
     * <br>
     * 请求大于
     * {@link  EnumIndexMapFinderTimesCached#CACHE_THRESHOLD}时，使用缓存，否则不使用缓存
     */
    public static final class EnumFinder {
        private EnumFinder() {
        }

        @SuppressWarnings({ "unchecked" })
        public static <T extends Enum<T> & IndexDesc> T findByIndexOrNull(Class<T> clazz, int index, boolean useCache) {
            Map<Integer, ? extends IndexDesc> cache = EnumIndexMapFinderCached.CACHE.getIfPresent(clazz);
            if (cache != null) {
                return (T) cache.get(index);
            }
            int value = 0;
            T res;
            try {
                value = EnumIndexMapFinderTimesCached.CACHE.get(clazz, () -> 0);
            } catch (ExecutionException e) {
                log.error("枚举缓存生成失败", e);
            }
            if (value - EnumIndexMapFinderTimesCached.CACHE_THRESHOLD < 0)
                EnumIndexMapFinderTimesCached.CACHE.put(clazz, ++value);
            final T[] enumConstants = clazz.getEnumConstants();
            if (useCache || value - EnumIndexMapFinderTimesCached.CACHE_THRESHOLD >= 0) {
                res = EnumIndexMapFinder.findByIndexOrNull(clazz, index, true);
            } else {
                res = EnumIndexLoopUpFinder.findByIndexOrNull(enumConstants, index);
            }
            if (value - EnumIndexMapFinderTimesCached.CACHE_THRESHOLD >= 0) {
                EnumIndexMapFinderTimesCached.CACHE.invalidate(clazz);
            }
            return res;
        }
    }

    /**
     * 根据index查找枚举
     *
     * @param clazz 实现了IndexDesc接口的枚举类
     * @param index 枚举的index
     * @param <T>   枚举类型
     * @return 枚举实体
     */
    public static <T extends Enum<T> & IndexDesc> T findByIndexOrNull(Class<T> clazz, int index) {
        return EnumFinder.findByIndexOrNull(clazz, index, false);
    }

    /**
     * 根据index查找枚举
     *
     * @param clazz    实现了IndexDesc接口的枚举类
     * @param index    枚举的index
     * @param useCache 是否使用缓存
     * @param <T>      枚举类型
     * @return 枚举实体
     */
    public static <T extends Enum<T> & IndexDesc> T findByIndexOrNull(Class<T> clazz, int index, boolean useCache) {
        return EnumFinder.findByIndexOrNull(clazz, index, useCache);
    }

    /**
     * 根据index查找枚举
     *
     * @param values 实现了IndexDesc接口的枚举数组
     * @param index  枚举的index
     * @param <T>    枚举类型
     * @return 枚举实体
     */
    public static <T extends Enum<T> & IndexDesc> T findByIndexOrNull(T[] values, int index) {
        return EnumIndexLoopUpFinder.findByIndexOrNull(values, index);
    }

    /**
     * 根据index查找枚举
     *
     * @param clazz 实现了IndexDesc接口的枚举类
     * @param index 枚举的index
     * @param <T>   枚举类型
     * @return 枚举实体，如果为null则抛出异常
     */
    public static <T extends Enum<T> & IndexDesc> T findByIndexOrThrow(Class<T> clazz, int index) {
        T entity = findByIndexOrNull(clazz, index);
        if (entity == null) {
            throw new IllegalArgumentException(clazz.getCanonicalName() + " 根据索引" + index + "找不到对应实体");
        }
        return entity;
    }

}
