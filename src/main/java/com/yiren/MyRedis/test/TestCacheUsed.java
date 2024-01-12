package com.yiren.MyRedis.test;

import com.yiren.MyRedis.ICache.ICache;
import com.yiren.MyRedis.ICache.impl.CacheBuilder;
import com.yiren.MyRedis.ICache.impl.CacheEvicts;
import com.yiren.MyRedis.一实现固定缓存大小.LruMap;
import com.yiren.design_patterns.单例模式.LazySingleton;
import com.yiren.principle.utils.Sleeper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public class TestCacheUsed {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCacheUsed.class);

    public static void main(String[] args) throws Exception {
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println("Original instance: " + instance);

        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(instance);
        oos.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        // 反序列化
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        LazySingleton deserializedInstance = (LazySingleton) ois.readObject();
        ois.close();

        System.out.println("Deserialized instance: " + deserializedInstance);
    }

    @Test
    public void test1() {
        LruMap<Integer, Integer> map = new LruMap<>(3);
        map.putValue(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        LOGGER.info(String.valueOf(map));
        map.put(4, 5);
        LOGGER.info(String.valueOf(map));
    }

    @Test
    public void test2() {
        ICache<Object, Object> cache = CacheBuilder
            .builder()
            .size(3)
            .map(new HashMap<>())
            .evict(CacheEvicts.fifo())
            .build();
        cache.put(1, 1);
        Sleeper.sleep();
        cache.expire(1, 10);
        Sleeper.sleep(5L);
        Object o = cache.get(1);
        System.out.println(o);
        Sleeper.sleep();
        o = cache.get(1);
        System.out.println(o);

    }

}
