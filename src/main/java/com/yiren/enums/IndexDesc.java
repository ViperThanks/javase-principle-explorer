package com.yiren.enums;

/**
 * Description: 枚举约束类，index为枚举值，desc为枚举描述。由于业务中的枚举值和枚举描述是强关联的，所以建议使用枚举类实现该接口
 * <br>
 * 建议使用IndexDescEnumUtil进行枚举值和枚举描述的映射
 * <pre>{@code
 *   @Getter
 *   @RequiredArgsConstructor
 *   public enum ExampleEnum implements IndexDesc {
 *      EXAMPLE(1, "演示", "asd2oji90wer");
 *      //这里必须要用Integer类型
 *      @JsonValue
 *      private final Integer index;
 *      private final String desc;
 *      //其他业务类型
 *      private final String code;
 *      @JsonCreator
 *      public static ExampleEnum findByIndexOrNull(int index) {
 *          return IndexDescEnumUtil.Finder.findByIndexOrNull(values(), index);
 *      }
 *      public static ExampleEnum findByCode(String code) {
 *          return IndexDescEnumUtil.Finder.findByCustomOrNull(values(), ExampleEnum::getCode, code);
 *      }
 *   }
 * }
 *
 * </pre>
 *
 * @author Viper Thanks
 * @see IndexDescEnumUtil
 * @since 28/6/2024
 */
public interface IndexDesc {
    /**
     * 枚举Index必须唯一且非空
     */
    Integer getIndex();

    /**
     * index的别名
     */
    default Integer getId() {
        return getIndex();
    }

    /**
     * 枚举描述
     */
    String getDesc();

    /**
     * 枚举描述的别名
     */
    default String getDescription() {
        return getDesc();
    }


    /**
     * 枚举之间是否相同
     */
    default boolean isSame(IndexDesc other) { return other == this; }


    /**
     * 是否相同
     */
    default boolean isSame(Integer index) {
        return index != null && getIndex() != null && index.intValue() == getIndex().intValue();
    }



}
