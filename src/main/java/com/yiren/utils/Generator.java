package com.yiren.utils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * desc: 数据生成器
 *
 * @author Viper Thanks
 * @since 17/5/2024
 */
public class Generator<T> {
    private final Supplier<? extends T> generator;
    private Predicate<? super T> predicate;

    private Generator() {
        throw new RuntimeException("no-arg construct is illegal");
    }

    private Generator(Supplier<? extends T> generator, Predicate<? super T> predicate) {
        this.generator = generator;
        this.predicate = predicate;
    }

    public Generator<T> clearPredicate(){
        this.predicate = t -> true;
        return this;
    }

    /**
     * 生成一个符合条件的单一元素。
     * @return 符合条件的单一元素
     * @throws NoSuchElementException 如果没有元素满足条件
     */
    public T one() {
        return Stream.generate(generator)
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No element satisfies the predicate"));
    }

    /**
     * 生成一个符合条件的元素列表。
     * @return 符合条件的元素列表
     */
    public List<T> list(int count) {
        return Stream.generate(generator)
                .filter(predicate)
                .limit(count)
                .collect(Collectors.toList());
    }

    public Set<T> set(int count) {
        return Stream.generate(generator)
                .filter(predicate)
                .limit(count)
                .collect(Collectors.toSet());
    }

    @SuppressWarnings("unchecked")
    public T[] array(int count) {
        Object[] result = Stream.generate(generator)
                .filter(predicate)
                .limit(count)
                .toArray();
        T[] array = (T[]) Array.newInstance(getNextNotNullEntity().getClass(), result.length);
        System.arraycopy(result, 0, array, 0, result.length);
        return array;
    }

    private static final int max_try_limit = 20;

    /**
     * 获取下一个不为null的实体
     */
    private T getNextNotNullEntity(){
        T entity;
        int count = 0;
        do {
            entity = generator.get();
        } while (entity == null && ++count < max_try_limit);
        if (count == max_try_limit) throw new RuntimeException("supplier is illegal ! because get() always null");
        return entity;
    }

    public static <K> GeneratorBuilder<K> newBuilder() {
        return new GeneratorBuilder<>();
    }

    public static class GeneratorBuilder<T> {
        private Supplier<? extends T> generator;
        private Predicate<? super T> predicate = t -> true;

        public GeneratorBuilder<T> generator(final Supplier<T> generator) {
            this.generator = generator;
            return this;
        }

        public GeneratorBuilder<T> predicate(final Predicate<T> predicate) {
            this.predicate = predicate;
            return this;
        }

        public Generator<T> build(){
            Objects.requireNonNull(generator,"generator must not null !");
            return new Generator<>(generator, predicate);
        }
    }
}
