package com.yiren.context;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * @author Viper Thanks
 * @see
 * @since 19/7/2024
 */
public class Context {
    public static final ConcurrentHashMap<String, Object> METHOD_MAP = new ConcurrentHashMap<>(16);

    @SuppressWarnings("unchecked")
    public static <T> T getExplore(Class<T> clazz) {
         return (T) METHOD_MAP.get(clazz.getCanonicalName());
    }
}
