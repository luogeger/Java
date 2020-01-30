package com.first.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author luoxiaoqing
 * @date 2018-01-27__19:35
 */
@Data
public class User extends Model<User> {

    private Long    id;

    private String  username;
}
