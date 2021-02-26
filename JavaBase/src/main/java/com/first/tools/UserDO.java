package com.first.tools;

import lombok.Data;

/**
 * @author luoxiaoqing
 * @date 2020-05-14__15:32
 * @desc
 */
@Data
public class UserDO {

    private Long userId;
    private String userName;
    private Integer age;
    private Integer sex;

    public UserDO() {

    }

    public UserDO(Long userId, String userName, Integer age, Integer sex) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.sex = sex;
    }
}
