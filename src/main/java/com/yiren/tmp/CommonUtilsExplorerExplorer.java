package com.yiren.tmp;

import com.alibaba.fastjson.JSON;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.utils.CommonUtils;
import com.yiren.utils.JSONUtils;

import java.util.Arrays;


/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 12/2/2024
 */
public class CommonUtilsExplorerExplorer extends BaseExplorer {


    /**
     * 探索 方法
     */
    @Override
    public void explore() throws Exception {
        int[] randomIntArr = CommonUtils.getRandomIntArr(5);
        log.info(Arrays.toString(randomIntArr));
        int[] ints = CommonUtils.defaultIf(arr -> arr.length == 5, randomIntArr, () -> new int[]{1, 2, 3, 4, 5});
        log.info(Arrays.toString(ints));
        log.info(JSONUtils.toJsonString(ints));
        log.info(JSON.toJSONString(ints));
    }

    public static void main(String[] args) {
        Executor.executeMyself();
    }


}