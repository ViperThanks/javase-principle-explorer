package com.yiren.principle.javase.大数据文件;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

import java.io.File;

/**
 * Description:
 *
 * @author Viper Thanks
 * @see
 * @since 24/7/2024
 */
public class BigDataReaderExplorer extends BaseExplorer {
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
        boolean b = BigDataFileGenerator.generate(new File("src/main/resources/testdata" + File.separator + "bigdata.txt"),
                100_000_000,true);
    }
}
