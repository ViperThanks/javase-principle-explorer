package com.yiren.init;

import com.yiren.context.Context;
import com.yiren.core.Explore;
import com.yiren.core.expand.BaseExplorer;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * Description:
 *
 * @author Viper Thanks
 * @see
 * @since 19/7/2024
 */
public class ExploreScanInitializer implements Initializer{

    private ExploreScanInitializer(){}

    public static ExploreScanInitializer getInstance() {
        return new ExploreScanInitializer();
    }

    private static final Logger LOG = LoggerFactory.getLogger(ExploreScanInitializer.class);

    @Override
    public void init() {
        Reflections reflections = new Reflections("com.yiren"); // 替换为您的包名
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Explore.class);

        for (Class<?> clazz : annotatedClasses) {
            if (BaseExplorer.class.isAssignableFrom(clazz)) { // 检查是否继承了 BaseExplorer
                try {
                    LOG.info("扫描到探索者 : {}", clazz.getCanonicalName());
                    Context.METHOD_MAP.put(clazz.getCanonicalName(), clazz.getDeclaredConstructor().newInstance());
                } catch (NoSuchMethodException e) {
                    // 处理异常：没有找到 explore 方法
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
