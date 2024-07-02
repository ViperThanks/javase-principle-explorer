package com.yiren.principle.dataStructure;

import com.alibaba.fastjson.TypeReference;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.entity.PrincipleField;
import com.yiren.entity.User;
import com.yiren.principle.utils.PrincipleUtil;

import java.util.ArrayList;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 25/6/2024
 */
public class ArrayListPrincipleExplorer extends BaseExplorer {

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
        final ArrayList<String> strings = (ArrayList<String>) StringGenerator.list(3);
        pws(strings);
        strings.add(1, "test");
        pws(strings);
        PrincipleField<Object[]> elementDataPrinciple = PrincipleUtil.getFiled(strings, "elementData", new TypeReference<Object[]>() {});
        Object[] elementData = elementDataPrinciple.get();
        elementData[7] = new User();
        pws(elementData);
        pws(strings.get(7));

    }
}
