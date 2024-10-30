package com.yiren.core.enums;

import com.yiren.enums.IndexDesc;
import com.yiren.utils.IndexDescEnumUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 3/7/2024
 */
@RequiredArgsConstructor
@Getter
public enum ExplorerType implements IndexDesc {
    NONE(0,"默认值"),
    JUC(1, "juc包"),
    JVM(2, "JVM虚拟机"),
    JAVA_SE(3, "Java基础"),
    SPRING(4,"Spring框架"),
    MYBATIS(5,"MyBatis框架"),
    ;
    private final Integer index;
    private final String desc;
    public static ExplorerType findByIndexOrNull(int index) {
        return IndexDescEnumUtils.findByIndexOrNull(ExplorerType.class, index);
    }

}
