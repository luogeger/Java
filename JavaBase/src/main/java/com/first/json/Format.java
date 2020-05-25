package com.first.json;

import com.alibaba.fastjson.JSON;

public class Format {
    public static String print (Object o){
        return JSON.toJSONString(o, true);
    }

}
