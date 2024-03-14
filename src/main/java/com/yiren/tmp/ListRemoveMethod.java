package com.yiren.tmp;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 13/3/2024
 */
public class ListRemoveMethod {
    public static void main(String[] args) {
        List<String> list1 = Lists.newArrayList("abc", "java", "abc", "abc", "yy", "abc", "xx", "abc", "abc");
        List<String> list2 = Lists.newArrayList("abc", "java", "abc", "abc", "yy", "abc", "xx", "abc", "abc");
        List<String> list3 = Lists.newArrayList("abc", "java", "abc", "abc", "yy", "abc", "xx", "abc", "abc");
        Iterator<String> iterator = list1.iterator();
        list2.removeIf(each -> Objects.equals(each, "abc"));
        for (int i = list3.size() - 1; i >= 0; i--) {
            if (Objects.equals(list3.get(i), "abc")) {
                list3.remove(i);
            }
        }
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        ArrayList<String> strings = new ArrayList<String>(2) {{
            add("1");
            add("2");
        }};
    }
}
