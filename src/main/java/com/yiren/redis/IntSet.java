package com.yiren.redis;

import com.google.common.primitives.Longs;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc: redis数据结构 int set的Java实现
 *
 * @author Viper Thanks
 * @since 9/4/2024
 */
@Data
public class IntSet {

    private static final Logger log = LoggerFactory.getLogger(IntSet.class);

    @Getter
    @RequiredArgsConstructor
    enum IntType {
        INT_64(Long.SIZE, Long.MIN_VALUE, Long.MAX_VALUE, null),
        INT_32(Integer.SIZE, Integer.MIN_VALUE, Integer.MAX_VALUE, INT_64),
        INT_16(Short.SIZE, Short.MIN_VALUE, Short.MAX_VALUE, INT_32);
        private final int size;
        private final long minValue;
        private final long maxValue;
        private final IntType upper;
    }

    private IntType intType;
    private int length;
    private byte[] elementData;

    public IntSet() {
        this.intType = IntType.INT_16;
        this.elementData = new byte[IntType.INT_16.getSize() * 160];
    }

    public boolean add(long num){
        checkNumRange(num);
        byte[] byteArray = Longs.toByteArray(num);
        int size = this.getIntType().getSize();
        System.arraycopy(byteArray, byteArray.length - size, elementData, 0, size);
        length++;
        return true;
    }

    private void checkNumRange(long num) {
        checkNumRange(num, this.getIntType());
    }

    private void checkNumRange(long num, IntType intType) {
        long minValue = intType.getMinValue();
        long maxValue = intType.getMaxValue();
        if (num < minValue || num > maxValue) {
            if (intType.getUpper() == null) {
                throw new UnsupportedOperationException("超出可以支持的范围");
            }
            checkNumRange(num, intType.getUpper());
        }else {
            if (this.intType != intType) {
                enlarge(intType);
            }
        }
    }

    private void enlarge(IntType intType) {
        log.info("enlarge!!!");
        this.intType = intType;
    }
}
