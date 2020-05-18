package com.first.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author luoxiaoqing
 * @date 2020-05-14__15:33
 * @desc
 */
@Data
public class UserVO {
    private Long userId;
    private String userName;
    private Integer age;
    private String sex;
}
