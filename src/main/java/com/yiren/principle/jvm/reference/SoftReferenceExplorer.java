package com.yiren.principle.jvm.reference;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 13/4/2024
 */
public class SoftReferenceExplorer extends BaseExplorer {

    public static void main(String[] args) {
        Executor.executeMyselfWithTime();
    }


    /**
     * 探索 方法
     *
     * @throws Exception
     */
    @Override
    public void explore() throws Exception {
        System.in.read();
    }
}
