package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.entity.PrincipleField;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 9/4/2024
 */
public class MyTest123 extends BaseExplorer {


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
        PrincipleField<int[]> length = new PrincipleField<>(new int[]{1, 2}, "length", int[].class);

    }
}
