package com.yiren.core.expand;

import com.yiren.utils.CommonUtils;
import org.slf4j.Logger;

/**
 * desc: 可以输出的接口
 *
 * @author Viper Thanks
 * @since 5/6/2024
 */
public interface Printable {

    Logger getLogger();

    /**
     * 分隔符
     */
    String SEPARATOR = "=====================%s========================";

    /**
     * 动态输出对象
     */
    default void print(Object o) {
        dynamicPrint(o);
    }

    /**
     * 动态输出对象
     */
    default void dynamicPrint(Object o) {
        if (getLogger() != null && getLogger().isInfoEnabled()) {
            getLogger().info(CommonUtils.toString(o));
        } else {
            System.out.println(CommonUtils.toString(o));
        }
    }

    /**
     * 动态输出对象，在上一行和下一行加入分隔符{@value SEPARATOR}
     */
    default void printWithSeparator(Object o) {
        printWithSeparator(o, "");
    }

    /**
     * 动态输出对象，在上一行和下一行加入分隔符{@value SEPARATOR}，sign为占位符的字符串
     */
    default void printWithSeparator(Object o, String sign) {
        print(String.format(SEPARATOR, sign));
        print(o);
        print(String.format(SEPARATOR, sign));
    }

}
