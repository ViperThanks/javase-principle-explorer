package com.yiren.tmp;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.tree.TreeNode;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.*;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Ints;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.utils.CommonUtils;
import com.yiren.utils.Json;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.checkerframework.checker.units.qual.C;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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