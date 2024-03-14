package com.yiren.principle.guava.io.source;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

/**
 * <p>
 * desc:    其实就是finally如果再跑异常，会把catch的异常给屏蔽掉
 * </p>
 *
 * @author weilin
 * @since 31/1/2024
 */
public class CloserExplorer extends BaseExplorer {


    /**
     * 探索 方法
     */
    @Override
    public void explore() throws Exception {

    }

    public static void main(String[] args) {
        Executor.executeMyself();
    }


}