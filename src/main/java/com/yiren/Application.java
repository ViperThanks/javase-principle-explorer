package com.yiren;

import com.yiren.enums.IndexDescEnumUtil;
import com.yiren.enums.IndexDescEnumUtilTest;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Viper Thanks
 * @see
 * @since 19/7/2024
 */
public class Application {


    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        List<IndexDescEnumUtilTest.TestEnum> list = new ArrayList<>(100000000);
        for (int i = 0; i < 10000000; i++) {
            IndexDescEnumUtilTest.TestEnum byIndexOrNull = IndexDescEnumUtil.Finder.findByIndexOrNull(IndexDescEnumUtilTest.TestEnum.class, RandomUtils.nextInt(1,26));
            list.add(byIndexOrNull);
        }
        log.info(list.get(0).toString());
    }
}
