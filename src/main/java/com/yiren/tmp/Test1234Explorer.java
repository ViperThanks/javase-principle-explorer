package com.yiren.tmp;

import cn.hutool.core.lang.tree.TreeNode;
import com.google.common.collect.*;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 6/2/2024
 */
public class Test1234Explorer extends BaseExplorer {


    /**
     * 探索 方法
     */
    @Override
    public void explore() throws Exception {
        TreeNode<Integer> treeNode = new TreeNode<>();
        treeNode.setExtra(ImmutableMap.of("1", 2, "3", 4));
        log.info(treeNode.toString());
        CharSequence name = treeNode.getName();


    }

    public static void main(String[] args) {
        Executor.executeMyself();
    }


}