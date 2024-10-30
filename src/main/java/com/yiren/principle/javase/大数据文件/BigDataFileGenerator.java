package com.yiren.principle.javase.大数据文件;

import com.google.common.collect.Lists;
import com.yiren.utils.GeneratorFactory;
import com.yiren.utils.JSONUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author Viper Thanks
 * @see
 * @since 24/7/2024
 */
@Slf4j
public class BigDataFileGenerator {

    private static final String userJson = JSONUtils.toJsonString(GeneratorFactory.UserGenerator.one());
    @SneakyThrows
    public static boolean generate(File file, int num,boolean clean) {
        List<String> jsons = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
             jsons.add(userJson);
        }
        log.info("start generate {}", file.getAbsolutePath());
        Path path = file.toPath();
        if (clean) {
            Files.deleteIfExists(path);
            Files.createFile(path);
        }
        List<List<String>> partition = Lists.partition(jsons, THREAD_SIZE);
        partition.forEach(list -> executorService.submit(() -> {
            try {
                Files.write(path, list, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));
        log.info("end generate {}", file.getAbsolutePath());

        return true;
    }

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors() / 2;

    static {
        log.info("THREAD_SIZE:{}", THREAD_SIZE);
    }

    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);
}
