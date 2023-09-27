package com.yiren.tmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yiren.algorithm.utils.AlgoUtils.range;

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
        int count = 0;
        for (int i : range(2)) {
            System.out.println(1);
            count++;

        }
        LOGGER.info(String.valueOf(count));
    }
}
