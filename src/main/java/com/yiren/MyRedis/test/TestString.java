package com.yiren.MyRedis.test;

import com.yiren.entity.PrincipleField;
import com.yiren.principle.utils.PrincipleUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public class TestString {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestString.class);


    public static void main(String[] args) throws Exception{
        Unsafe unsafe = PrincipleUtil.getUnsafe();
        System.out.println(unsafe);
    }
}
