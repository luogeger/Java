package com.first.json;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoxiaoqing
 * @date 2020-01-29__16:43
 */
@Data
public class School {

    private String id;
    private String name;
    List<User> students = new ArrayList<User>();

}
