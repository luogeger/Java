package com.first.json.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * 格式化输出json
 * <pre>
 *      System.out.println(user.toString());
 *      User(id=1, name=lucy, age=18)
 *
 *      System.out.println(JSON.toJSONString(user));
 *      {"age":18,"id":"1","name":"lucy"}
 *
 *      System.out.println(Format.print(user));
 *      {
 *         "age":18,
 *         "id":"1",
 *         "name":"lucy"
 *      }
 * </pre>
 * @author luoxiaoqing
 */
public class Format {
    public static String print (Object o){
        return JSON.toJSONString(o, true);
    }

}
