package com.yiren.utils;

import com.yiren.core.expand.Testable;
import com.yiren.core.expand.Factory;
import com.yiren.entity.User;

/**
 * desc: 默认数据生成器工厂
 *
 * @author Viper Thanks
 * @since 21/5/2024
 */
public interface GeneratorFactory extends Factory {
    Generator<String> StringGenerator = Generator.<String>newBuilder()
            .generator(CommonUtils::getRandomString)
            .build();
    Generator<Integer> IntGenerator = Generator.<Integer>newBuilder()
            .generator(() -> CommonUtils.getRandomInt(100))
            .build();
    Generator<Double> DoubleGenerator = Generator.<Double>newBuilder()
            .generator(() -> CommonUtils.getRandomDouble(100))
            .build();
    Generator<User> UserGenerator = Generator.<User>newBuilder()
            .generator(Testable::getUser)
            .build();
}