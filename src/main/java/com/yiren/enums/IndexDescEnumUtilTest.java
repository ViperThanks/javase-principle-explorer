package com.yiren.enums;

import com.yiren.utils.JSONUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class IndexDescEnumUtilTest {


    @RequiredArgsConstructor
    @Getter
    public enum TestEnum implements IndexDesc {
        ONE(1, "One", "One", Arrays.asList(2, 3, 4))
            , TWO(2, "Two", "Two", Arrays.asList(1, 3, 4))
            , THREE(3, "Three", "Three", Arrays.asList(1, 2, 4))
            , FOUR(4, "Four", "Four", Arrays.asList(1, 2, 3))
            , FIVE(5, "Five", "Five", Arrays.asList(1, 2, 3))
            , SIX(6, "Six", "Six", Arrays.asList(1, 2, 3))
            , SEVEN(7, "Seven", "Seven", Arrays.asList(1, 2, 3))
            , EIGHT(8, "Eight", "Eight", Arrays.asList(1, 2, 3))
            , NINE(9, "Nine", "Nine", Arrays.asList(1, 2, 3))
            , TEN(10, "Ten", "Ten", Arrays.asList(1, 2, 3))
            , ELEVEN(11, "Eleven", "Eleven", Arrays.asList(1, 2, 3)),
            TWELVE(12, "Twelve", "Twelve", Arrays.asList(1, 2, 3))
            , THIRTEEN(13, "Thirteen", "Thirteen", Arrays.asList(1, 2, 3))
            , FOURTEEN(14, "Fourteen", "Fourteen", Arrays.asList(1, 2, 3))
            , FIFTEEN(15, "Fifteen", "Fifteen", Arrays.asList(1, 2, 3))
            , SIXTEEN(16, "Sixteen", "Sixteen", Arrays.asList(1, 2, 3))
            , SEVENTEEN(17, "Seventeen", "Seventeen", Arrays.asList(1, 2, 3))
            , EIGHTEEN(18, "Eighteen", "Eighteen", Arrays.asList(1, 2, 3))
            , NINETEEN(19, "Nineteen", "Nineteen", Arrays.asList(1, 2, 3))
            , TWENTY(20, "Twenty", "Twenty", Arrays.asList(1, 2, 3))
            , TWENTY_ONE(21, "TwentyOne", "TwentyOne", Arrays.asList(1, 2, 3))
            , TWENTY_TWO(22, "TwentyTwo", "TwentyTwo", Arrays.asList(1, 2, 3))
            , TWENTY_THREE(23, "TwentyThree", "TwentyThree", Arrays.asList(1, 2, 3))
            , TWENTY_FOUR(24, "TwentyFour", "TwentyFour", Arrays.asList(1, 2, 3))
            , TWENTY_FIVE(25, "TwentyFive", "TwentyFive", Arrays.asList(1, 2, 3))
        ;


        private final Integer index;
        private final String code;
        private final String desc;
        private final List<Integer> list;


        @Override
        public Integer getIndex() {
            return index;
        }

        @Override
        public String getDesc() {
            return desc;
        }

        public static TestEnum findByCode(String code) {
            Map<String, TestEnum> customEntityMap = IndexDescEnumUtil.MapFactory.getCustomEntityMap(TestEnum.values(), TestEnum::getCode);
            return IndexDescEnumUtil.Finder.findByCustomOrThrow(values(), TestEnum::getCode, code);
        }

        public static TestEnum findByList(List<Integer> list) {
            return IndexDescEnumUtil.Finder.findByCustomOrNull(values(), TestEnum::getList, list);
        }




    }

    @Test
    public void testFindByCode() {
        JSONUtils.toJsonString("123");
    }

    @Test
    public void testFindByIndexOrNull() {
        boolean same = TestEnum.EIGHT.isSame(TestEnum.EIGHT);
        log.info(String.valueOf(same));
    }

    private static String getCacheKey(Class<?> clazz, String prefix) {
        return prefix + clazz;
    }

    @Test
    public void testFindByCustomOrNull() {

        TestEnum result = IndexDescEnumUtil.Finder.findByCustomOrNull(TestEnum.class, TestEnum::getDesc, "Two");
        assertNotNull(result);


        result = IndexDescEnumUtil.Finder.findByCustomOrNull(TestEnum.class, TestEnum::getDesc, "Four");
        assertNull(result);
    }

    @Test
    public void testFindByIndexOrThrow() {
        TestEnum result = IndexDescEnumUtil.Finder.findByIndexOrThrow(TestEnum.class, 3);


        Exception exception = assertThrows(NullPointerException.class, () -> {
            IndexDescEnumUtil.Finder.findByIndexOrThrow(TestEnum.class, 4);
        });
        assertEquals("根据[index]:4找不到对应的TestEnum实体", exception.getMessage());
    }
}