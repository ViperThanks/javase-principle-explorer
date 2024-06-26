package com.yiren.principle.jvm.reference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 13/4/2024
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;
    private String name;
    private Double score;
    private String className;
}
