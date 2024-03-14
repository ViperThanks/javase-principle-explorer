package com.yiren.tmp;

import cn.hutool.core.collection.ArrayIter;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.TransCollection;
import cn.hutool.core.img.ImgUtil;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.yiren.utils.CommonUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 24/2/2024
 */
public class JavaCacheUtils {
    public static void main(String[] args) {
        Iterable<Integer> iterable = new ArrayList<>();
        FluentIterable<Integer> from = FluentIterable.from(ArrayUtils.toObject(CommonUtils.range(10)));
        try (Stream<?> stream = ImmutableList.of().stream()){
            
        }
    }
}
