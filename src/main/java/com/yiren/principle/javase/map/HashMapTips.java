package com.yiren.principle.javase.map;

import com.google.common.collect.Maps;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.entity.PrincipleField;
import com.yiren.principle.utils.PrincipleUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Map;

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
        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        PrincipleField<Object[]> elementData = PrincipleUtil.getFiled(list, "elementData",Object[].class);
        log.info(elementData.toString());
    }

    private void map2(){
        Map<Integer,Integer> map = Maps.newHashMapWithExpectedSize(2);
        PrincipleField<?> filed = PrincipleUtil.getFiled(map, "table");
        log.info(String.valueOf(ArrayUtils.getLength(filed.get())));
        map.put(1, 1);
        log.info(String.valueOf(ArrayUtils.getLength(filed.get())));
        map.put(2, 2);
        log.info(String.valueOf(ArrayUtils.getLength(filed.get())));
    }
}
