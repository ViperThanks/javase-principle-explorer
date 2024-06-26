package com.yiren.utils;

/**
 * desc: 调用者工具类
 *
 * @author Viper Thanks
 * @since 20/6/2024
 */
public final class CallerUtils {

    public static String getCallerClassName() {
        final StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        final StackTraceElement callerStackTrace = stackTraceElements[3];
        return callerStackTrace.getClassName();
    }

    public static Class<?> getCallerClass() {
        final StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        final StackTraceElement callerStackTrace = stackTraceElements[3];
        try {
            return Class.forName(callerStackTrace.getClassName());
        } catch (Exception ex) {
            return null;
        }
    }

}
