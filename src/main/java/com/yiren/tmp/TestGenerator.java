package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.redis.SortedSet;

import static com.yiren.redis.SortedSet.*;


/**
 * desc:
 *
 * @author Viper Thanks
 * @since 31/5/2024
 */
public class TestGenerator extends BaseExplorer {

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
        SortedSet sortedSet = create();
        SortedSet.zadd(sortedSet, "1", 2.0);
        p(SortedSet.get(sortedSet.getUniqueName()));
    }

}
