package com.yiren.principle.guava.io.file;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.FluentIterable;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 29/1/2024
 */
public class FilesExplorer extends BaseExplorer {

    public static void main(String[] args) {
        Executor.executeMyself();
    }

    private static final File words = new File("E:\\codespaces\\eden\\javase-principle-explorer\\src\\main\\resources\\testdata\\words.txt");

    @Override
    public void explore() throws Exception {
//        readAndHandle();
//        hash();
//        write();
        treeFilesPreOrderTraversal();
    }

    private void treeFilesPreOrderTraversal() {
        Iterable<File> files = Files.fileTraverser().depthFirstPreOrder(new File("E:\\codespaces\\eden\\javase-principle-explorer\\src"));
        files.forEach(file -> log.info(String.valueOf(file)));

    }

    private void write() throws IOException {
        File file = new File(words.getParent(), "/words1.txt");
        Files.copy(words, file);
        Files.asCharSink(file, StandardCharsets.UTF_8, FileWriteMode.APPEND).write("原神，启动!");
        List<String> strings = Files.readLines(file, StandardCharsets.UTF_8);
        assertEquals(strings.get(strings.size() - 1), "原神，启动!");
        file.deleteOnExit();
    }

    private void hash() throws IOException {
        final String secretKey = "原神";
        HashCode hash = Files.asByteSource(words).hash(Hashing.sha256());
        log.info(String.valueOf(hash.asLong()));
    }

    private void readAndHandle() throws IOException {
        Files.asCharSource(words, StandardCharsets.UTF_8).forEachLine(log::info);

        /*这里false直接截断*/

        List<Integer> integers = Files.asCharSource(words, StandardCharsets.UTF_8).readLines(new LineProcessor<List<Integer>>() {

            private final List<Integer> LIST = new ArrayList<>();

            @Override
            public boolean processLine(String line) throws IOException {
                return LIST.add(line.length());
            }

            @Override
            public List<Integer> getResult() {
                return LIST;
            }
        });
        log.info(JSON.toJSONString(integers, true));
    }
}
