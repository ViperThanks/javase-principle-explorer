package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

import java.util.Arrays;
import java.util.List;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 24/4/2024
 */
public class TestPrincipleExplorer extends BaseExplorer {

    public static void main(String[] args) {
        Executor.executeMyselfWithTime();
    }


    /**
     * 探索 方法
     *
     */
    @Override
    public void explore() throws Exception {
        Integer[] ints = {1, 2, 3, 4, 5, 6};
        List<Integer> list = Arrays.asList(ints);
        printWithSeparator(list);
        ints[0] = 2;
        printWithSeparator(list);
    }

}
