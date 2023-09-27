package com.yiren.principle.javase.零拷贝技术;

import com.google.common.base.Stopwatch;
import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.utils.Memory;
import com.yiren.utils.MemoryUtil;
import com.yiren.utils.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <h2>
 * desc    :
 * </h2>
 *
 * @author : weilin
 * @since : 31/8/2023
 */
public class ZeroCopyExplorer implements Explorer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZeroCopyExplorer.class);


    public static void main(String[] args) {
        Executor.executeExplorer(ZeroCopyExplorer.class);
    }


    /**
     * 探索 方法
     *
     * @throws Exception
     */
    @Override
    public void explore() throws Exception {
        normalCopy();
        zeroCopy();
    }


    public void normalCopy() throws Exception {

        long start = System.currentTimeMillis();

        //core  code
        FileInputStream inputStream = new FileInputStream("d:/Tmp/test_data/_1GB.txt");
        FileOutputStream outputStream = new FileOutputStream(String.format("d:/Tmp/test_data/_1GB%s.txt", ThreadLocalRandom.current().nextLong()));
        byte[] buffer = MemoryUtil.getBytes(1);
        while (inputStream.read(buffer) >= 0) {
            outputStream.write(buffer);
        }
        outputStream.flush();

        long end = System.currentTimeMillis();
        inputStream.close();
        outputStream.close();
        LOGGER.info("normalCopy execute time {} ms", end - start);
    }

    public void zeroCopy() throws Exception {

        long start = System.currentTimeMillis();

        //core  code
        File from = new File("d:/Tmp/test_data/_1GB.txt");
        File to = new File(String.format("d:/Tmp/test_data/_1GB%s.txt", ThreadLocalRandom.current().nextLong()));


        try (FileChannel fromChannel = new RandomAccessFile(from, "r").getChannel(); FileChannel toChannel = new RandomAccessFile(to, "rw").getChannel()) {
            fromChannel.transferTo(0, from.length(), toChannel);
        } catch (FileNotFoundException e) {
            LOGGER.error("文件不存在 {}", from.getAbsolutePath());
        }

        long end = System.currentTimeMillis();

        LOGGER.info("zeroCopy execute time {} ms", end - start);
    }
}
