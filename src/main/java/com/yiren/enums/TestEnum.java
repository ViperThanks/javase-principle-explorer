package com.yiren.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TestEnum implements IndexDesc {
    w(1, "One", "One");


    private final Integer index;
    private final String code;
    private final String desc;



    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public static TestEnum findByCode(String code) {
        return IndexDescEnumUtil.Finder.findByCustomOrThrow(values(), TestEnum::getCode, code);
    }


}
