package com.yiren.principle.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;
import com.yiren.algorithm.array.HeapSort;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.principle.guava.io.source.EncodingExplorer;
import com.yiren.utils.CommonUtils;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 24/2/2024
 */
public class EventBudsExplorer extends BaseExplorer {
    /**
     * 探索 方法
     *
     * @throws Exception
     */
    @Override
    public void explore() throws Exception {
        EventBus eventBus = new EventBus("1");
        eventBus.post("java yyds");

    }

    @Subscribe
    public void hello(Object param){
        log.info((String) param);
    }

    public static void main(String[] args) {
        Executor.executeMyself();
    }
}
