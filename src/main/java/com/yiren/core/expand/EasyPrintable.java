package com.yiren.core.expand;

/**
 * desc: 对输出的接口进行简写
 *
 * @author Viper Thanks
 * @since 5/6/2024
 */
public interface EasyPrintable extends Printable {

    /**
     * 动态输出对象
     */
    default void p(Object o) {
        print(o);
    }

    /**
     * 动态输出对象，在上一行和下一行加入分隔符{@value SEPARATOR}
     */
    default void pws(Object o){
        pws(o, "");
    }
    /**
     * 动态输出对象，在上一行和下一行加入分隔符{@value SEPARATOR}，sign为占位符的字符串
     */
    default void pws(Object o, String sign) {
        printWithSeparator(o, sign);
    }
}
