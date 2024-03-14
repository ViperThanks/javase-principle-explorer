package com.yiren.tmp;

import com.yiren.entity.User;
import com.yiren.principle.guava.cache.LinkedHashLRUCache;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 28/2/2024
 */
public class RefactorTest {
    public static void main(String[] args) {
        First first = new First(Arrays.asList(new Second(), new Second(Arrays.asList("1", "2", "3"))));
        List<First> firstList = Collections.singletonList(first);
        //判断list包不包含4
        boolean b = firstList.stream()
                .flatMap(first1 -> first1.getSecondList().stream())
                .flatMap(second -> second.getStringList().stream())
                .anyMatch(str -> Objects.equals("4", str));


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static final class First {
        private List<Second> secondList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static final class Second{
        private List<String> stringList;
    }

    @Data
    private static final class Third{
        private List<String> stringList;
    }
}
