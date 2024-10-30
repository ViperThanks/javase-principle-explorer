package com.yiren.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Range;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 4/7/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Interval {
    private int start;
    private int end;

    public boolean isOverlapping(Interval other) {
        return isOverlapping(this, other);
    }

    public static boolean isOverlapping(Interval obj, Interval other) {
        checkNotNull(obj, "obj is null");
        checkNotNull(other, "other is null");
        Range<Integer> thisRange = Range.between(obj.start, obj.end);
        Range<Integer> otherRange = Range.between(other.getStart(), other.getEnd());
        return thisRange.isOverlappedBy(otherRange);
    }

    public static boolean isAnyOverlapping(Interval obj, Collection<Interval> other) {
        return checkNotNull(other, "other is null").stream().anyMatch(item -> isOverlapping(obj, item));
    }

}
