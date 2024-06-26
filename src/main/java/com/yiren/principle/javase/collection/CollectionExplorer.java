package com.yiren.principle.javase.collection;

import com.google.common.collect.Lists;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

import java.util.ArrayList;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 15/3/2024
 */
public class CollectionExplorer extends BaseExplorer {

    public static void main(String[] args) {
        Executor.executeMyself();
    }

    /**
     * 探索 方法
     *
     * @throws Exception
     */
    @Override
    public void explore() throws Exception {
//        Collection<Integer> collection = Lists.newArrayList(CommonUtils.getRandomArr(10, ThreadLocalRandom.current()::nextInt));
//        //开始操作
//        collection.add(1);
//        print(collection);
//        collection.addAll(Collections.emptyList());
//        boolean equals = collection.equals(Collections.emptyList());
//
//        printWithSeparator(equals);
//
//        Collection<Integer> collection1 = new LinkedList<>();
//        ArrayList<Integer> integers = Lists.newArrayList(CommonUtils.getRandomArr(10, ThreadLocalRandom.current()::nextInt));
//        integers.trimToSize();
        ArrayList<Integer> integers =
                Lists.newArrayList(1, 23, 2, 4, 5, 5, 2, 5, 6);
        ArrayList<Integer> integers1 = (ArrayList<Integer>) integers.clone();
        integers.set(1, 2);
        log.info(String.valueOf(integers));
        log.info(String.valueOf(integers1));
    }
}
