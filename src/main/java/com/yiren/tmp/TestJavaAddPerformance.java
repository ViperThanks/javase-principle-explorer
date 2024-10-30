package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 4/6/2024
 */
public class TestJavaAddPerformance extends BaseExplorer {


    public static void main(String[] args) {
        Executor.executeMyselfWithTime();
    }



    /**
     * 探索 方法
     *
     * @throws Exception e
     */
    @Override
    public void explore() throws Exception {
        long count = 0;
        for (long i = 0; i < 1_000_000_00; i++) {
            count += i;
        }
        pws(count, "count");
    }
}
