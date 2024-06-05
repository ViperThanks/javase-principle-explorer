package com.yiren.core.expand;

/**
 * desc: 对输出的接口进行简写
 *
 * @author Viper Thanks
 * @since 5/6/2024
 */
public interface EasyPrintable extends Printable {

    default void p(Object o) {
        print(o);
    }

    default void pws(Object o){
        printWithSeparator(o);
    }

    default void pws(Object o, String sign) {
        printWithSeparator(o, sign);
    }
}
