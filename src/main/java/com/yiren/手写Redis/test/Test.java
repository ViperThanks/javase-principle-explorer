package com.yiren.手写Redis.test;

import com.yiren.手写Redis.一实现固定缓存大小.LruMap;
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
public class Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) {
        LruMap<Integer, Integer> map = new LruMap<>(3);
        map.putValue(1, 2);
        map.putValue(2, 3);
        map.putValue(3, 4);
        LOGGER.info(String.valueOf(map));
        map.putValue(4, 5);
        LOGGER.info(String.valueOf(map));
    }

    @org.junit.jupiter.api.Test
    public void test1(){
        LruMap<Integer, Integer> map = new LruMap<>(3);
        map.putValue(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        LOGGER.info(String.valueOf(map));
        map.put(4, 5);
        LOGGER.info(String.valueOf(map));
    }

}
