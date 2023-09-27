package com.yiren.tmp;

import com.yiren.algorithm.datastructure.Pair;
import com.yiren.principle.javase.零拷贝技术.ZeroCopyExplorer;
import com.yiren.utils.MemoryUtil;
import com.yiren.utils.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.beans.beancontext.BeanContext;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.WildcardType;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 4/9/2023
 */
public class Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) throws Exception {
        LOGGER.info("start");
        ZeroCopyExplorer explorer = new ZeroCopyExplorer();
        long l = StopWatch.calculateMilliseconds(() -> {
            try {
                explorer.normalCopy();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        LOGGER.info("cost time : {} ms", l);
        LOGGER.info("finish");
    }


    private static void generateFile() throws Exception {

        String fatherPath = "D:\\Tmp\\test_data\\";
        String _1GB = "_1GB.txt";
        File _1GBFile = new File(fatherPath + _1GB);
        OutputStream stream = Files.newOutputStream(_1GBFile.toPath(), StandardOpenOption.WRITE);
        byte[] buffed = MemoryUtil.getBytes(1 << 2);
        Arrays.fill(buffed, (byte) 6);
        for (int i = 0; i < 1 << 8; i++) {
            stream.write(buffed, 0, buffed.length - 1);
        }
        stream.flush();
        stream.close();


    }


    @org.junit.jupiter.api.Test
    public void test1() {
        Pair<Integer, Integer> pair = new Pair<>(1, 2);
        Integer key = pair.getKey();
        System.out.println(key);
    }
}
