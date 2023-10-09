package com.yiren.tmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public class TestWhy {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestWhy.class);


    public static void main(String[] args) {



        int[] array2 = new int[50];
        int index = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                array2[index++] = i;
            }
        }
        System.out.println(Arrays.toString(array2));
    }
}
