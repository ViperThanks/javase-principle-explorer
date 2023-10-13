package com.yiren.MyRedis.test;

import java.util.Collections;
import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.Field;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public class TestString {

    public static void main(String[] args) throws Exception{
        String s = new String("1234567890");
        String printable = ClassLayout.parseInstance(s).toPrintable();
        System.out.println(printable);
        Field value = String.class.getDeclaredField("value");
        value.setAccessible(true);
        String printable1 = ClassLayout.parseInstance(value).toPrintable();
        System.out.println(printable1);
    }
}
