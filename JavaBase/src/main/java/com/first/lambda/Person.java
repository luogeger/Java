package com.first.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author luoxiaoqing
 */
@Data
@AllArgsConstructor
public class Person {
    
    private String  firstName;
    private String  lastName;
    private Integer age;
}
