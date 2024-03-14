package com.yiren.principle.guava.io.source;

import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.utils.CommonUtils;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 30/1/2024
 */
public class CharSourceExplorer extends BaseExplorer {

    public static void main(String[] args) {
        Executor.executeMyself();
    }

    @Override
    public void explore() throws Exception {

        String content = CommonUtils.getRandomString(1000) + "\n" + CommonUtils.getRandomString(100);
        CharSource.wrap(content).forEachLine(line -> System.out.println(line.length()));
        CharSource concat = CharSource.concat(CharSource.wrap(content + "1"), CharSource.wrap(content + "2"));
    }
}
