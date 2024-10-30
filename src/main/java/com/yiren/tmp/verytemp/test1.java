package com.yiren.tmp.verytemp;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 25/6/2024
 */
@Slf4j
public class test1 {

    private static final String ID_PREFIX = "ACCID_";

    public static String encryptId(String id) {
        return ID_PREFIX + RandomStringUtils.randomNumeric(6) + "_" + id;
    }

    public static String decryptId(String encryptedId) {
        // 1. Decrypt the original encrypted string (assuming you have a decrypt method)
        // String originalEncryptedString = decrypt(encryptedId);

        // 2. Split the string based on the underscore
        String[] parts = encryptedId.split("_");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid encrypted ID format.");
        }
        return parts[2];
    }
    public static void main(String[] args) {

    }

    public static <T> void filter(List<T> list, Predicate<T> predicate) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (!predicate.test(element)) {
                iterator.remove();
            }
        }
    }

    public static <T> void filter(List<T> list, Function<T, Boolean> predicate) {
        list.removeIf(element -> !predicate.apply(element));
    }

    public static void test(String id) {
        try {
            throw new RuntimeException("test");
        } catch (RuntimeException e) {
            log.error("{}::test::{}",id, e);
        }
    }

    private static List<String> getPhoneListByContact(String remarkPhone) {
        BigDecimal.valueOf()
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static final class BaseDateRangeDto implements java.io.Serializable{
        private Date beginDate;
        private Date endDate;

        public static BaseDateRangeDto of(Date beginDate, Date endDate) {
            return new BaseDateRangeDto(beginDate, endDate);
        }

        public List<Date> intervalDateList(){
            if (beginDate == null || endDate == null) {
                throw new IllegalArgumentException("日期范围不能为空");
            }
            if (beginDate.after(endDate)) {
                throw new IllegalArgumentException("开始时间不能大于结束时间");
            }
            List<Date> dateList = new ArrayList<>();
            while (beginDate.before(endDate)) {
                dateList.add(beginDate);
                beginDate = DateUtil.offsetDay(beginDate, 1);
            }
            dateList.add(endDate);
            return dateList;
        }

    }
}
