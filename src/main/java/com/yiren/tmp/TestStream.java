package com.yiren.tmp;

import com.yiren.utils.CommonUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 20/4/2024
 */
public class TestStream {
    private static final Logger log = LoggerFactory.getLogger(TestStream.class);

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>(1000);
        for (int i = 1; i <= 1000; i++) {
            Student student = new Student();
            student.setId(CommonUtils.getRandomLong(100));
            student.setName(CommonUtils.getRandomString());
            student.setClassId(CommonUtils.getRandomLong(100));
            student.setDeleted(ThreadLocalRandom.current().nextBoolean());
            students.add(student);
        }
        Set<Long> classIdSet = new HashSet<>();
        for (Student student : students) {
            classIdSet.add(student.getClassId());
        }
    }



    @Data
    private static final class Student {
        Long id;
        String name;
        Long ClassId;
        Boolean deleted;
    }
    @Data
    private static final class Class{
        Long id;
        String name;
    }
}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 1 << 15);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

}



