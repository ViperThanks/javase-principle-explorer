package com.yiren.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Description: 实现了IndexDesc接口的枚举工具类
 * 当枚举数量大于 {@value Config#THRESHOLD} 的时候，使用Map缓存，否则使用循环查找
 * 缓存最大值为 {@value Config#MAX_CACHE_SIZE}
 * <br>
 * 方法都包含useCache参数的重载，当枚举查找次数过多时，建议使用
 * <br>
 * example ：
 * <pre>{@code
 * ExampleEnum targetEnum = IndexDescEnumUtil.Finder.findByIndexOrNull(ExampleEnum.class, 2);
 * Map<Integer, ExampleEnum> map = IndexDescEnumUtil.MapFactory.getIndexEntityMap(ExampleEnum.class);
 * }</pre>
 * <br>
 * 在枚举内部：
 * <pre>{@code
 * public static ExampleEnum findByIndexOrNull(int index) {
 *     return IndexDescEnumUtil.Finder.findByIndexOrNull(values(), index);
 * }
 *
 * public static ExampleEnum findByCodeOrNull(String code){
 *     return IndexDescEnumUtil.Finder.findByCustomOrNull(values(), IndexDesc::getCode, code);
 * }
 * }</pre>
 * Created by weilin on 24/7/2024.
 *
 * @author Viper Thanks
 * @version 0.1.1
 * @see IndexDesc
 * @since 24/7/2024
 */
@SuppressWarnings({"unused"})
public final class IndexDescEnumUtil {

    private IndexDescEnumUtil() {
    }

    private static final Logger log = LoggerFactory.getLogger(IndexDescEnumUtil.class);

    /**
     * 配置类
     */
    private static final class Config {

        /**
         * 是否强制使用缓存
         */
        private static boolean FORCE_CACHE = false;

        /**
         * map查找阈值
         */
        private static int THRESHOLD = 1 << 4;

        /**
         * 最大缓存大小
         */
        private static int MAX_CACHE_SIZE = 1000;

    }

    /**
     * 设置强制使用缓存
     *
     * @param forceCache 是否强制使用
     */
    public static synchronized void setForceCache(boolean forceCache) {
        log.info("枚举查找器强制使用缓存修改为 : {}", forceCache);
        Config.FORCE_CACHE = forceCache;
    }

    private static boolean isForceCache() {
        return Config.FORCE_CACHE;
    }

    /**
     * 设置枚举缓存阈值
     *
     * @param threshold 阈值
     */
    public static synchronized void setThreshold(int threshold) {
        log.info("枚举查找器阈值修改为 : {}", threshold);
        Config.THRESHOLD = threshold;
    }

    private static int getThreshold() {
        return Config.THRESHOLD;
    }

    /**
     * 设置枚举缓存最大数量
     *
     * @param maxCacheSize 最大缓存数量
     */
    public static synchronized void setMaxCacheSize(int maxCacheSize) {
        log.info("枚举查找器缓存最大数量修改为 : {}", maxCacheSize);
        Config.MAX_CACHE_SIZE = maxCacheSize;
    }

    private static int getMaxCacheSize() {
        return Config.MAX_CACHE_SIZE;
    }

    /**
     * function提取
     */
    private static final Function<IndexDesc, Integer> INDEX_DESC_INDEX_FUNCTION = s -> s.getIndex();
    private static final Function<IndexDesc, String> INDEX_DESC_DESC_FUNCTION = s -> s.getDesc();

    private static final String NOT_FOUND_EXCEPTION_INDEX_MESSAGE_TEMPLATE = "根据 index[%s] 找不到对应的枚举值，请检查枚举类: %s --> 存在的值 [%s]";
    private static final String NOT_FOUND_EXCEPTION_CUSTOM_MESSAGE_TEMPLATE = "根据 自定义条件[%s] 找不到对应的枚举值，请检查枚举类: %s --> 存在的值 [%s]";
    private static final String PARAMETER_EXCEPTION_MESSAGE_TEMPLATE = "参数 [%s] 不能为 null";
    private static final String ENUM_ENTITY_ARRAY_EMPTY_ERROR_MESSAGE = "枚举内部没有任何对象";

    /**
     * 枚举查找器接口类，定义基本查找接口
     */
    interface IndexDescEnumFinder {

        /**
         * 判断是否支持
         *
         * @param values 需要判断的枚举类实体数组
         * @return 支持与否
         */
        <T extends Enum<T> & IndexDesc> boolean isSupported(T[] values);

        /**
         * 判断是否支持
         *
         * @param clazz 需要判断的枚举类
         * @return 支持与否
         */
        default <T extends Enum<T> & IndexDesc> boolean isSupported(Class<T> clazz) {
            return isSupported(clazz.getEnumConstants());
        }

        /**
         * 根据index查找，可能为null
         *
         * @param values 需要查找的枚举类实体数组
         * @param index  需要查找的index
         * @return index对应实体，可能为null
         */
        @Nullable
        <T extends Enum<T> & IndexDesc> T findByIndexOrNull(T[] values, int index);

        /**
         * 根据index查找，可能为null
         *
         * @param clazz 需要查找的枚举类
         * @param index 需要查找的index
         * @return index对应实体，可能为null
         */
        @Nullable
        default <T extends Enum<T> & IndexDesc> T findByIndexOrNull(Class<T> clazz, int index) {
            return findByIndexOrNull(clazz.getEnumConstants(), index);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param values      需要查找的枚举类实体数组
         * @param mapping     映射函数
         * @param expectValue 期待的值
         * @return 对应实体，可能为null
         */
        @Nullable
        <T extends Enum<T> & IndexDesc, R> T findByCustomOrNull(T[] values, Function<T, R> mapping, R expectValue);

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param clazz       需要查找的枚举类
         * @param mapping     映射函数
         * @param expectValue 期待的值
         * @return 对应实体，可能为null
         */
        @Nullable
        default <T extends Enum<T> & IndexDesc, R> T findByCustomOrNull(Class<T> clazz, Function<T, R> mapping, R expectValue) {
            return findByCustomOrNull(clazz.getEnumConstants(), mapping, expectValue);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param values    需要查找的枚举类实体数组
         * @param predicate 断言函数
         * @return 对应实体，可能为null
         */
        @Nullable
        <T extends Enum<T> & IndexDesc> T findByCustomOrNull(T[] values, Predicate<T> predicate);

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param clazz     需要查找的枚举类
         * @param predicate 断言函数
         * @return 对应实体，可能为null
         */
        @Nullable
        default <T extends Enum<T> & IndexDesc> T findByCustomOrNull(Class<T> clazz, Predicate<T> predicate) {
            return findByCustomOrNull(clazz.getEnumConstants(), predicate);
        }

        /**
         * 根据index查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.IndexDescEnumFinder#findByIndexOrNull(Class, int)} )}
         */
        @Nonnull
        default <T extends Enum<T> & IndexDesc> T findByIndexOrThrow(Class<T> clazz, int index) {
            return findByIndexOrThrow(clazz.getEnumConstants(), index);
        }

        /**
         * 根据index查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.IndexDescEnumFinder#findByIndexOrNull(Enum[], int)} )}
         */
        @Nonnull
        default <T extends Enum<T> & IndexDesc> T findByIndexOrThrow(T[] values, int index) {
            return Objects.requireNonNull(findByIndexOrNull(values, index), () -> values.length == 0 ? ENUM_ENTITY_ARRAY_EMPTY_ERROR_MESSAGE :
                    String.format(NOT_FOUND_EXCEPTION_INDEX_MESSAGE_TEMPLATE,
                            index,
                            values[0].getClass().getName(),
                            Arrays.stream(values).map(INDEX_DESC_INDEX_FUNCTION).map(String::valueOf).collect(Collectors.joining(", "))));
        }

        /**
         * 根据自定义条件查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.IndexDescEnumFinder#findByCustomOrNull(Class, Function, Object)} )}
         */
        @Nonnull
        default <T extends Enum<T> & IndexDesc, R> T findByCustomOrThrow(Class<T> clazz, Function<T, R> mapping, R expectValue) {
            return findByCustomOrThrow(clazz.getEnumConstants(), mapping, expectValue);
        }

        /**
         * 根据自定义条件查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.IndexDescEnumFinder#findByCustomOrNull(Enum[], Function, Object)} )}
         */
        @Nonnull
        default <T extends Enum<T> & IndexDesc, R> T findByCustomOrThrow(T[] values, Function<T, R> mapping, R expectValue) {
            return Objects.requireNonNull(findByCustomOrNull(values, mapping, expectValue), () -> values.length == 0 ? ENUM_ENTITY_ARRAY_EMPTY_ERROR_MESSAGE :
                    String.format(NOT_FOUND_EXCEPTION_CUSTOM_MESSAGE_TEMPLATE,
                            expectValue, values[0].getClass().getName(),
                            Arrays.stream(values).map(mapping).map(String::valueOf).collect(Collectors.joining(", "))));
        }
    }

    private static final class LoopUpFinder implements IndexDescEnumFinder {

        private static final LoopUpFinder INSTANCE = new LoopUpFinder();

        private LoopUpFinder() {

        }

        public static LoopUpFinder getInstance() {
            return INSTANCE;
        }


        @Override
        public <T extends Enum<T> & IndexDesc> boolean isSupported(T[] values) {
            return values.length <= getThreshold();
        }

        @Override
        @Nullable
        public <T extends Enum<T> & IndexDesc> T findByIndexOrNull(T[] values, int index) {
            Objects.requireNonNull(values, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values"));
            for (T value : values) if (Objects.equals(value.getIndex(), index)) return value;
            return null;
        }

        @Override
        @Nullable
        public <T extends Enum<T> & IndexDesc, R> T findByCustomOrNull(T[] values, Function<T, R> mapping, R expectValue) {
            Objects.requireNonNull(values, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values"));
            Objects.requireNonNull(mapping, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "mapping"));
            Objects.requireNonNull(expectValue, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "expectValue"));
            for (T value : values) if (Objects.equals(mapping.apply(value), (expectValue))) return value;
            return null;
        }

        @Override
        @Nullable
        public <T extends Enum<T> & IndexDesc> T findByCustomOrNull(T[] values, Predicate<T> predicate) {
            Objects.requireNonNull(values, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values"));
            Objects.requireNonNull(predicate, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "predicate"));
            for (T value : values) if (predicate.test(value)) return value;
            return null;
        }
    }

    private static final class MapFinder implements IndexDescEnumFinder {

        private static final MapFinder INSTANCE = new MapFinder();

        private static final String PREFIX_INDEX_ENTITY_MAP_KEY = "INDEX_ENTITY_MAP_KEY:";
        private static final String PREFIX_CUSTOM_ENTITY_MAP_KEY = "CUSTOM_ENTITY_MAP_KEY:";

        private static String getCacheKey(Class<?> clazz, String prefix) {
            return prefix + clazz.getName();
        }

        private MapFinder() {
        }

        public static MapFinder getInstance() {
            return INSTANCE;
        }


        @Override
        public <T extends Enum<T> & IndexDesc> boolean isSupported(T[] values) {
            return values.length > getThreshold();
        }

        @Nullable
        @Override
        @SuppressWarnings({"unchecked"})
        public <T extends Enum<T> & IndexDesc> T findByIndexOrNull(T[] values, int index) {
            Objects.requireNonNull(values, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values"));
            Objects.requireNonNull(values[0], () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values[0]"));
            String key = getCacheKey(values[0].getClass(), PREFIX_INDEX_ENTITY_MAP_KEY);
            Map<Integer, T> map = Cached.get(key);
            if (map != null) {
                return map.get(index);
            }
            map = MapFactory.getIndexEntityMap(values);
            Cached.put(key, map);
            return map.get(index);
        }

        @Nullable
        @Override
        @SuppressWarnings({"unchecked"})
        public <T extends Enum<T> & IndexDesc, R> T findByCustomOrNull(T[] values, Function<T, R> mapping, R expectValue) {
            Objects.requireNonNull(values, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values"));
            Objects.requireNonNull(mapping, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "mapping"));
            Objects.requireNonNull(expectValue, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "expectValue"));
            String key = getCacheKey(values[0].getClass(), PREFIX_CUSTOM_ENTITY_MAP_KEY);
            Map<R, T> map = Cached.get(key);
            if (map != null) {
                return map.get(expectValue);
            }
            map = MapFactory.getCustomEntityMap(values, mapping);
            Cached.put(key, map);
            return map.get(expectValue);
        }

        @Nullable
        @Override
        public <T extends Enum<T> & IndexDesc> T findByCustomOrNull(T[] values, Predicate<T> predicate) {
            // todo 优化
            return FinderFactory.DEFAULT_FINDER.findByCustomOrNull(values, predicate);
        }
    }


    /**
     * 缓存内部类，基于ConcurrentHashMap实现
     */
    @SuppressWarnings({"rawtypes"})
    private static final class Cached {
        private Cached() {
        }

        /**
         * 缓存实体
         */
        private static final Map<String, Map> cache = new ConcurrentHashMap<>(16);

        /**
         * 添加缓存
         */
        private static void put(String clazz, Map map) {
            if (cache.size() > getMaxCacheSize()) {
                log.warn("枚举缓存已满，清除缓存");
                cache.clear();
            }
            cache.putIfAbsent(clazz, map);
        }

        /**
         * 获取对应的map
         */
        private static Map get(String clazz) {
            return cache.get(clazz);
        }

        /**
         * 判断是否包含
         */
        private static boolean contains(String clazz) {
            return get(clazz) != null;
        }


    }

    /**
     * map生成器
     */
    public static final class MapFactory {
        private MapFactory() {
        }

        /**
         * 获取索引与实体的映射map，并缓存
         * for ex:
         * enum StudentType: (1:"一年级"),(2,"二年级")
         * return: {1:StudentType.一年级,2:StudentType.二年级}
         *
         * @param clazz 枚举Class
         * @return map
         */
        public static <T extends Enum<T> & IndexDesc> Map<Integer, T> getIndexEntityMap(@Nonnull Class<T> clazz) {
            Objects.requireNonNull(clazz, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "clazz"));
            return Arrays.stream(clazz.getEnumConstants()).collect(Collectors.toMap(INDEX_DESC_INDEX_FUNCTION, Function.identity(), (v1, v2) -> v1));
        }

        /**
         * 获取索引与描述的映射map，并缓存
         * for ex:
         * enum StudentType: (1:"一年级"),(2,"二年级")
         * return: {1:"一年级",2:"二年级"}
         *
         * @param clazz 枚举Class
         * @return map
         */
        public static <T extends Enum<T> & IndexDesc> Map<Integer, String> getIndexDescMap(@Nonnull Class<T> clazz) {
            Objects.requireNonNull(clazz, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "clazz"));
            return Arrays.stream(clazz.getEnumConstants()).collect(Collectors.toMap(INDEX_DESC_INDEX_FUNCTION, INDEX_DESC_DESC_FUNCTION, (v1, v2) -> v1));
        }

        /**
         * 获取索引与实体的映射map，并缓存
         * for ex:
         * enum StudentType: (1:"一年级"),(2,"二年级")
         * return: {1:StudentType.一年级,2:StudentType.二年级}
         *
         * @param values 枚举实体数组
         * @return map
         */
        public static <T extends Enum<T> & IndexDesc> Map<Integer, T> getIndexEntityMap(@Nonnull T[] values) {
            Objects.requireNonNull(values, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values"));
            return Arrays.stream(values).collect(Collectors.toMap(INDEX_DESC_INDEX_FUNCTION, Function.identity(), (v1, v2) -> v1));
        }

        /**
         * 获取索引与描述的映射map，并缓存
         * for ex:
         * enum StudentType: (1:"一年级"),(2,"二年级")
         * return: {1:"一年级",2:"二年级"}
         *
         * @param values 枚举实体数组
         * @return map
         */
        public static <T extends Enum<T> & IndexDesc> Map<Integer, String> getIndexDescMap(@Nonnull T[] values) {
            Objects.requireNonNull(values, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values"));
            return Arrays.stream(values).collect(Collectors.toMap(INDEX_DESC_INDEX_FUNCTION, INDEX_DESC_DESC_FUNCTION, (v1, v2) -> v1));
        }

        /**
         * 获取自定义映射map，并缓存
         *
         * @param clazz   枚举Class
         * @param mapping IndexDesc::getOther
         * @return map
         */
        public static <T extends Enum<T> & IndexDesc, R> Map<R, T> getCustomEntityMap(@Nonnull Class<T> clazz, @Nonnull Function<T, R> mapping) {
            Objects.requireNonNull(clazz, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "clazz"));
            Objects.requireNonNull(mapping, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "mapping"));
            return Arrays.stream(clazz.getEnumConstants()).collect(Collectors.toMap(mapping, Function.identity(), (v1, v2) -> v1));
        }

        /**
         * 获取自定义映射map，并缓存
         *
         * @param values  枚举实体数组
         * @param mapping IndexDesc::getOther
         * @return map
         */
        public static <T extends Enum<T> & IndexDesc, R> Map<R, T> getCustomEntityMap(@Nonnull T[] values, @Nonnull Function<T, R> mapping) {
            Objects.requireNonNull(values, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values"));
            Objects.requireNonNull(mapping, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "mapping"));
            return Arrays.stream(values).collect(Collectors.toMap(mapping, Function.identity(), (v1, v2) -> v1));
        }
    }

    /**
     * 可以查找的枚举查找器接口
     */
    interface FinderFindable {

        @Nonnull
        <T extends Enum<T> & IndexDesc> IndexDescEnumFinder getCacheFinder();


        @Nonnull
        <T extends Enum<T> & IndexDesc> IndexDescEnumFinder getFinder(@Nonnull Class<T> clazz);

        @Nonnull
        <T extends Enum<T> & IndexDesc> IndexDescEnumFinder getFinder(@Nonnull T[] values);

        @Nonnull
        default <T extends Enum<T> & IndexDesc> IndexDescEnumFinder getFinder(@Nonnull Class<T> clazz, boolean useCache) {
            return useCache || isForceCache() ? getCacheFinder() : getFinder(clazz);
        }


        @Nonnull
        default <T extends Enum<T> & IndexDesc> IndexDescEnumFinder getFinder(@Nonnull T[] values, boolean useCache) {
            return useCache || isForceCache() ? getCacheFinder() : getFinder(values);
        }

    }

    /**
     * 枚举查找器工厂
     */
    private static final class FinderFactory implements FinderFindable {

        /**
         * 缓存查找器
         */
        private static final IndexDescEnumFinder CACHE_FINDER = MapFinder.getInstance();

        /**
         * 查找器list，使用策略模式
         */
        private static final IndexDescEnumFinder[] FINDERS = new IndexDescEnumFinder[]{LoopUpFinder.getInstance(), MapFinder.getInstance()};

        /**
         * 默认查找器
         */
        private static final IndexDescEnumFinder DEFAULT_FINDER = LoopUpFinder.getInstance();

        private FinderFactory() {
        }

        private static final FinderFactory INSTANCE = new FinderFactory();

        public static FinderFactory getInstance() {
            return INSTANCE;
        }

        @Nonnull
        @Override
        public <T extends Enum<T> & IndexDesc> IndexDescEnumFinder getCacheFinder() {
            return CACHE_FINDER;
        }

        /**
         * 获取Finder，基于策略模式
         *
         * @param clazz 枚举Class
         * @return finder
         */
        @Nonnull
        public <T extends Enum<T> & IndexDesc> IndexDescEnumFinder getFinder(@Nonnull Class<T> clazz) {
            Objects.requireNonNull(clazz, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "clazz"));
            for (int i = 0; i < FINDERS.length; i++) if (FINDERS[i].isSupported(clazz)) return FINDERS[i];
            return DEFAULT_FINDER;
        }

        /**
         * 获取Finder，基于策略模式
         */
        @Nonnull
        public <T extends Enum<T> & IndexDesc> IndexDescEnumFinder getFinder(@Nonnull T[] values) {
            Objects.requireNonNull(values, () -> String.format(PARAMETER_EXCEPTION_MESSAGE_TEMPLATE, "values"));
            for (int i = 0; i < FINDERS.length; i++) if (FINDERS[i].isSupported(values)) return FINDERS[i];
            return DEFAULT_FINDER;
        }
    }

    /**
     * 门面查找器
     * 当枚举数量大于 {@value Config#THRESHOLD} 的时候，使用Map缓存，否则使用循环查找
     * 也可以使用重载方法的参数useCache参数指定使用缓存
     */
    public static final class Finder {

        private Finder() {
        }

        private static final FinderFactory FINDER_FACTORY = FinderFactory.getInstance();

        /**
         * 根据index查找，可能为null
         *
         * @param clazz 需要查找的枚举类
         * @param index 需要查找的index
         * @return index对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc> T findByIndexOrNull(@Nonnull Class<T> clazz, int index) {
            return findByIndexOrNull(clazz, index, false);
        }

        /**
         * 根据index查找，可能为null
         *
         * @param clazz    需要查找的枚举类
         * @param index    需要查找的index
         * @param useCache 是否使用缓存
         * @return index对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc> T findByIndexOrNull(@Nonnull Class<T> clazz, int index, boolean useCache) {
            return FINDER_FACTORY.getFinder(clazz, useCache).findByIndexOrNull(clazz, index);
        }

        /**
         * 根据index查找，可能为null
         *
         * @param values 需要查找的枚举类实体数组
         * @param index  需要查找的index
         * @return index对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc> T findByIndexOrNull(@Nonnull T[] values, int index) {
            return findByIndexOrNull(values, index, false);
        }

        /**
         * 根据index查找，可能为null
         *
         * @param values   需要查找的枚举类实体数组
         * @param index    需要查找的index
         * @param useCache 是否使用缓存
         * @return index对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc> T findByIndexOrNull(@Nonnull T[] values, int index, boolean useCache) {
            return FINDER_FACTORY.getFinder(values, useCache).findByIndexOrNull(values, index);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param clazz       需要查找的枚举类
         * @param mapping     映射函数
         * @param expectValue 期待的值
         * @return 对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc, R> T findByCustomOrNull(@Nonnull Class<T> clazz, @Nonnull Function<T, R> mapping, @Nonnull R expectValue) {
            return findByCustomOrNull(clazz, mapping, expectValue, false);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param clazz       需要查找的枚举类
         * @param mapping     映射函数
         * @param expectValue 期待的值
         * @param useCache    是否使用缓存
         * @return 对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc, R> T findByCustomOrNull(@Nonnull Class<T> clazz, @Nonnull Function<T, R> mapping, @Nonnull R expectValue, boolean useCache) {
            return FINDER_FACTORY.getFinder(clazz, useCache).findByCustomOrNull(clazz, mapping, expectValue);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param values      需要查找的枚举类实体数组
         * @param mapping     映射函数
         * @param expectValue 期待的值
         * @return 对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc, R> T findByCustomOrNull(@Nonnull T[] values, @Nonnull Function<T, R> mapping, @Nonnull R expectValue) {
            return findByCustomOrNull(values, mapping, expectValue, false);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param values      需要查找的枚举类实体数组
         * @param mapping     映射函数
         * @param expectValue 期待的值
         * @param useCache    是否使用缓存
         * @return 对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc, R> T findByCustomOrNull(@Nonnull T[] values, @Nonnull Function<T, R> mapping, @Nonnull R expectValue, boolean useCache) {
            return FINDER_FACTORY.getFinder(values, useCache).findByCustomOrNull(values, mapping, expectValue);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param clazz     需要查找的枚举类
         * @param predicate 断言函数
         * @return 对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc> T findByCustomOrNull(@Nonnull Class<T> clazz, @Nonnull Predicate<T> predicate) {
            return findByCustomOrNull(clazz, predicate, false);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param clazz     需要查找的枚举类
         * @param predicate 断言函数
         * @param useCache  是否使用缓存
         * @return 对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc> T findByCustomOrNull(@Nonnull Class<T> clazz, @Nonnull Predicate<T> predicate, boolean useCache) {
            return FINDER_FACTORY.getFinder(clazz, useCache).findByCustomOrNull(clazz, predicate);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param values    需要查找的枚举类实体数组
         * @param predicate 断言函数
         * @return 对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc> T findByCustomOrNull(@Nonnull T[] values, @Nonnull Predicate<T> predicate) {
            return findByCustomOrNull(values, predicate, false);
        }

        /**
         * 根据自定义条件查找，可能为null
         *
         * @param values    需要查找的枚举类实体数组
         * @param predicate 断言函数
         * @param useCache  是否使用缓存
         * @return 对应实体，可能为null
         */
        @Nullable
        public static <T extends Enum<T> & IndexDesc> T findByCustomOrNull(@Nonnull T[] values, @Nonnull Predicate<T> predicate, boolean useCache) {
            return FINDER_FACTORY.getFinder(values, useCache).findByCustomOrNull(values, predicate);
        }

        /**
         * 根据index查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.Finder#findByIndexOrNull(Class, int)} )}
         */
        @Nonnull
        public static <T extends Enum<T> & IndexDesc> T findByIndexOrThrow(@Nonnull Class<T> clazz, int index) {
            return findByIndexOrThrow(clazz, index, false);
        }

        /**
         * 根据index查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.Finder#findByIndexOrNull(Class, int, boolean)} )}
         */
        @Nonnull
        public static <T extends Enum<T> & IndexDesc> T findByIndexOrThrow(@Nonnull Class<T> clazz, int index, boolean useCache) {
            return FINDER_FACTORY.getFinder(clazz, useCache).findByIndexOrThrow(clazz, index);
        }

        /**
         * 根据index查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.Finder#findByIndexOrNull(Enum[], int)} )}
         */
        @Nonnull
        public static <T extends Enum<T> & IndexDesc> T findByIndexOrThrow(@Nonnull T[] values, int index) {
            return findByIndexOrThrow(values, index, false);
        }

        /**
         * 根据index查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.Finder#findByIndexOrNull(Enum[], int, boolean)} )}
         */
        @Nonnull
        public static <T extends Enum<T> & IndexDesc> T findByIndexOrThrow(@Nonnull T[] values, int index, boolean useCache) {
            return FINDER_FACTORY.getFinder(values, useCache).findByIndexOrThrow(values, index);
        }

        /**
         * 根据自定义条件查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.Finder#findByCustomOrNull(Class, Function, Object)} )}
         */
        @Nonnull
        public static <T extends Enum<T> & IndexDesc, R> T findByCustomOrThrow(@Nonnull Class<T> clazz, @Nonnull Function<T, R> mapping, @Nonnull R expectValue) {
            return findByCustomOrThrow(clazz, mapping, expectValue, false);
        }

        /**
         * 根据自定义条件查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.Finder#findByCustomOrNull(Class, Function, Object, boolean)} )}
         */
        @Nonnull
        public static <T extends Enum<T> & IndexDesc, R> T findByCustomOrThrow(@Nonnull Class<T> clazz, @Nonnull Function<T, R> mapping, @Nonnull R expectValue, boolean useCache) {
            return FINDER_FACTORY.getFinder(clazz, useCache).findByCustomOrThrow(clazz, mapping, expectValue);
        }

        /**
         * 根据自定义条件查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.Finder#findByCustomOrNull(Enum[], Function, Object)} )}
         */
        @Nonnull
        public static <T extends Enum<T> & IndexDesc, R> T findByCustomOrThrow(@Nonnull T[] values, @Nonnull Function<T, R> mapping, @Nonnull R expectValue) {
            return FINDER_FACTORY.getFinder(values, false).findByCustomOrThrow(values, mapping, expectValue);
        }

        /**
         * 根据自定义条件查找，如果为null则抛出异常
         * 详细注解看{@link IndexDescEnumUtil.Finder#findByCustomOrNull(Enum[], Function, Object, boolean)} )}
         */
        @Nonnull
        public static <T extends Enum<T> & IndexDesc, R> T findByCustomOrThrow(@Nonnull T[] values, @Nonnull Function<T, R> mapping, @Nonnull R expectValue, boolean useCache) {
            return FINDER_FACTORY.getFinder(values, useCache).findByCustomOrThrow(values, mapping, expectValue);
        }
    }
}
