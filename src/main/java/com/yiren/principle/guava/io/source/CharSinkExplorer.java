package com.yiren.principle.guava.io.source;

import com.google.common.io.CharSink;
import com.google.common.primitives.Doubles;
import com.google.common.util.concurrent.RateLimiter;
import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.core.expand.BaseExplorer;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 30/1/2024
 */
public class CharSinkExplorer extends BaseExplorer {

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(10);

    public static void main(String[] args) {
        Executor.executeMyself();
    }

    @Override
    public void explore() throws Exception {

        System.out.println(Double.toString(Math.pow(20, 7)));
    }
}
