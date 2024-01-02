package com.yiren.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.Function;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-12-22
 */
public class LogicEqualUtils {

  @SafeVarargs
  public static <T, R> boolean isLogicallyEqual(T that, T other, Function<T, R>... functions) {
    return isLogicallyEqual(that, other, Arrays.asList(functions));
  }


  public static <T, R> boolean isLogicallyEqual(T that, T other, List<Function<T, R>> functions) {
    EqualsBuilder equalsBuilder = new EqualsBuilder();
    if (functions instanceof RandomAccess) for (int i = 0; i < functions.size(); i++)equalsBuilder.append(functions.get(i).apply(that), functions.get(i).apply(other));
    else for (Function<T, R> function : functions)equalsBuilder.append(function.apply(that), function.apply(other));
    return equalsBuilder.isEquals();
  }

}
