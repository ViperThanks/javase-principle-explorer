package com.yiren.core;


import com.google.common.base.Stopwatch;
import com.yiren.utils.CallerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * desc    : 执行者类，封装了一些执行方法
 * 用反射去实现的，就是不想每次都去new一个对象
 */
public final class Executor {

    private static final Logger LOGGER = LoggerFactory.getLogger("Explorer Core Executor");

    private static final String BEGIN_SIGN = "------------>";
    private static final String END_SIGN = "<------------";

    enum State {
        NO_START(0),
        RUNNING(1),
        TERMINAL(2);
        private final int value;

        public int getValue() {
            return value;
        }

        State(int value) {
            this.value = value;
        }
    }

    private static final Map<Class<? extends Explorer>, State> map = new ConcurrentHashMap<>(16);

    @Deprecated
    public static void executeExplorer(Explorer explorer) {
        try {
            LOGGER.info("{} execute explorerClass: {} {}\n", BEGIN_SIGN, explorer.getClass().getSimpleName(), END_SIGN);
            explorer.explore();
        } catch (Exception e) {
            LOGGER.error("catch explorer inner error \n ", e);
        }
        LOGGER.info("{} execute explorerClass: {} main thread end {}\n", BEGIN_SIGN, explorer.getClass().getSimpleName(), END_SIGN);
    }

    /**
     * 通过字节码对象创建对象,并执行
     *
     * @param explorerClass 探索者类的Class对象
     */
    @SafeVarargs
    public static void executeExplorer(Class<? extends Explorer>... explorerClass) {
        for (Class<? extends Explorer> explorerClazz : explorerClass) {
            if (explorerClazz == null) {
                LOGGER.error("explorer is null");
                continue;
            }
            String clazzName = explorerClazz.getSimpleName();
            LOGGER.info("{} execute explorerClass: [{}] main thread start {}", BEGIN_SIGN, clazzName, END_SIGN);
            Explorer explorer = null;
            try {
                explorer = explorerClazz.getConstructor().newInstance();
            } catch (Throwable e) {
                LOGGER.error("{} execute explorerClass: [{}] error {}", BEGIN_SIGN, clazzName, END_SIGN);
                LOGGER.error("ensure that the explorerClass has a no-args constructor");
                LOGGER.error("ensure that the explorerClass constructor is public");
                continue;
            }
            boolean success = true;
            try {
                explorer.explore();
            } catch (Throwable e) {
                LOGGER.error("catch inner exception", e);
                success = false;
            }
            LOGGER.info("{} execute explorerClass: [{}] main thread end because {} {}", BEGIN_SIGN, clazzName,
                    success ? "execution completed" : "upper exception", END_SIGN);
        }
    }

    @SafeVarargs
    public static void executeExplorerWithTime(Class<? extends Explorer>... explorerClass) {
        for (Class<? extends Explorer> explorerClazz : explorerClass) {
            if (explorerClazz == null) {
                LOGGER.error("explorer is null");
                continue;
            }
            String clazzName = explorerClazz.getSimpleName();
            LOGGER.info("{} execute explorerClass: [{}] main thread start {}", BEGIN_SIGN, clazzName, END_SIGN);
            if (explorerClazz.isAnnotationPresent(Explore.class)) {
                Explore annotation = explorerClazz.getAnnotation(Explore.class);
                String desc = annotation.desc();
                LOGGER.info("{} 探索类型：{}，探索内容：{} {}", BEGIN_SIGN, annotation.value().getDesc(), desc, END_SIGN);
            }
            Explorer explorer = null;
            try {
                explorer = explorerClazz.getConstructor().newInstance();
            } catch (Throwable e) {
                LOGGER.error("{} execute explorerClass: [{}] error {}", BEGIN_SIGN, clazzName, END_SIGN);
                LOGGER.error("ensure that the explorerClass has a no-args constructor");
                LOGGER.error("ensure that the explorerClass constructor is public");
                continue;
            }
            boolean success = true;
            Stopwatch started = Stopwatch.createStarted();
            try {
                explorer.explore();
            } catch (Throwable e) {
                LOGGER.error("catch inner exception", e);
                success = false;
            }
            Stopwatch stop = started.stop();
            LOGGER.info("{} execute explorerClass: [{}] main thread end because {} {}", BEGIN_SIGN, clazzName,
                    success ? "execution completed" : "upper exception", END_SIGN);
            LOGGER.info("{} execute explorerClass: [{}] used {} {}", BEGIN_SIGN, clazzName, stop, END_SIGN);
        }
    }

    /**
     * 基于反射获取运行时class然后再反射调用
     */
    @SuppressWarnings("unchecked")
    public static void executeMyselfWithTime() {
        Class<?> clazz = null;
        try {
            // 加载类并创建实例
            clazz = CallerUtils.getCallerClass();
            // 检查是否存在 explore 方法并调用
            if (!Explorer.class.isAssignableFrom(clazz)) {
                throw new IllegalStateException("illegal class because no implement Explorer");
            }
            if (map.get(clazz) == State.RUNNING || map.get(clazz) == State.TERMINAL) {
                throw new IllegalStateException("double or more run explorer method");
            }
            map.put((Class<? extends Explorer>) clazz, State.RUNNING);
            executeExplorerWithTime((Class<? extends Explorer>) clazz);
        } catch (Exception e) {
            LOGGER.error("Error in doMyself method", e);
        } finally {
            map.put((Class<? extends Explorer>) clazz, State.TERMINAL);
        }
    }


    /**
     * 基于反射获取运行时class然后再反射调用
     */
    @SuppressWarnings("unchecked")
    public static void executeMyself() {

        // 获取当前线程的栈跟踪元素
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        // stackTraceElements[2] 是调用 doMyself 的方法
        StackTraceElement caller = stackTraceElements[2];
        String className = caller.getClassName();
        Class<?> clazz = null;
        try {
            // 加载类并创建实例
            clazz = Class.forName(className);
            // 检查是否存在 explore 方法并调用
            if (!Explorer.class.isAssignableFrom(clazz)) {
                throw new IllegalStateException("illegal class because no implement Explorer");
            }
            if (map.get(clazz) == State.RUNNING || map.get(clazz) == State.TERMINAL) {
                throw new IllegalStateException("double or more run explorer method");
            }
            map.put((Class<? extends Explorer>) clazz, State.RUNNING);
            executeExplorer((Class<? extends Explorer>) clazz);
        } catch (Exception e) {
            LOGGER.error("Error in doMyself method", e);
        } finally {
            map.put((Class<? extends Explorer>) clazz, State.TERMINAL);
        }
    }

}
