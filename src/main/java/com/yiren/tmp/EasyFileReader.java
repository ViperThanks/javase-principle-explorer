package com.yiren.tmp;

import com.google.common.base.Splitter;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 27/5/2024
 */
public class EasyFileReader {


    private static final Logger log = LoggerFactory.getLogger(EasyFileReader.class);

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\testfile\\admin\\testfile\\bill_one.txt");
        Files.asCharSource(file, StandardCharsets.UTF_8).forEachLine(line -> log.info(Splitter.on(';').splitToList(line).subList(0, 5).toString()));
    }
    public static void test(){

    }
}
