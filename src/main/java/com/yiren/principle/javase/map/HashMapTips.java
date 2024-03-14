package com.yiren.principle.javase.map;

import com.google.common.collect.Maps;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.entity.PrincipleField;
import com.yiren.principle.utils.PrincipleUtil;
import com.yiren.utils.CommonUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 17/1/2024
 */
public class HashMapTips extends BaseExplorer {


    public static void main(String[] args) {
        Executor.executeExplorer(HashMapTips.class);
    }

    @Override
    public void explore() throws Exception {

        map1();
        log.info(SEPARATOR);
        map2();
    }

    private void map1() {
        Map<Integer,Integer> map = new HashMap<>(2);
        PrincipleField filed = PrincipleUtil.getFiled(map, "table");
        log.info(String.valueOf(ArrayUtils.getLength(filed.get())));
        map.put(1, 1);
        log.info(String.valueOf(ArrayUtils.getLength(filed.get())));
        map.put(2, 2);
        log.info(String.valueOf(ArrayUtils.getLength(filed.get())));
    }

    private void map2(){
        Map<Integer,Integer> map = Maps.newHashMapWithExpectedSize(2);
        PrincipleField filed = PrincipleUtil.getFiled(map, "table");
        log.info(String.valueOf(ArrayUtils.getLength(filed.get())));
        map.put(1, 1);
        log.info(String.valueOf(ArrayUtils.getLength(filed.get())));
        map.put(2, 2);
        log.info(String.valueOf(ArrayUtils.getLength(filed.get())));
    }
}
