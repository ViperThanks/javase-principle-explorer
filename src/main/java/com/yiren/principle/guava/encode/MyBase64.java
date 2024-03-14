package com.yiren.principle.guava.encode;

import com.google.common.base.Preconditions;

import java.nio.charset.StandardCharsets;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 24/2/2024
 */
public final class MyBase64 {

    private static final String dict_str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final char[] dict = dict_str.toCharArray();

    public static String encode(final String plain) {
        return encode(plain.getBytes());
    }
    public static String encode(final byte[] plain) {
        checkNotNull(plain);
        return doEncode(plain);
    }

    private static String doEncode(final byte[] plain) {
        final StringBuilder builder = new StringBuilder();
        
        return null;
    }

    private static char get(final int index){
        if (index < 0 || index > dict.length) {
            throw new IllegalArgumentException("base64 encode error");
        }
        return dict[index];
    }
}
