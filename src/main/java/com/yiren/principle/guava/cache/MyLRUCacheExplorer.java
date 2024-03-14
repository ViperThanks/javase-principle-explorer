package com.yiren.principle.guava.cache;

import cn.hutool.core.collection.CollectionUtil;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

import java.util.HashMap;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 24/2/2024
 */
public class MyLRUCacheExplorer extends BaseExplorer {
    /**
     * 探索 方法
     *
     * @throws Exception
     */
    @Override
    public void explore() throws Exception {
        LinkedHashLRUCache<Integer, Integer> lruCache = new LinkedHashLRUCache<>(2);
        lruCache.put(1, 2);
        lruCache.put(2, 3);
        log.info(lruCache.toString());
        lruCache.put(3, 4);
        log.info(lruCache.toString());
        CollectionUtil.isEmpty(new HashMap<>());
    }

    public static void main(String[] args) {
        Executor.executeMyself();
    }
}
