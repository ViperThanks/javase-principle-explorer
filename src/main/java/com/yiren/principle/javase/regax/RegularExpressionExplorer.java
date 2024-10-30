package com.yiren.principle.javase.regax;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author Viper Thanks
 * @see
 * @since 29/7/2024
 */
public class RegularExpressionExplorer extends BaseExplorer {

    private static final List<String> param = Arrays.asList("google", "runoob", "taobao");
    private static final String base_str = Joiner.on(" ").join(param);
    /**
     * 探索 方法
     *
     * @throws Exception e
     */
    @Override
    public void explore() throws Exception {
        String regex = "[aeiou]";
        Pattern compile = Pattern.compile(regex);
        log.info(JSON.toJSONString(compile.split(base_str)));
    }

    public static void main(String[] args) {
        Executor.executeMyselfWithTime();
    }
}
