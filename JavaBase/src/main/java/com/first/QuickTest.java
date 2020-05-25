package com.first;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.first.json.Format;
import com.first.lombok.User;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author luoxiaoqing
 * @date 2020-02-05__03:06
 */
public class QuickTest {

    /**
     * 获取类名和全类名
     */
    @Test
    public void main1 () {
        Class<QuickTest> quickTestClass = QuickTest.class;
        System.out.println(quickTestClass);
        System.out.println(quickTestClass);

        System.out.println("image".toLowerCase());

    }


    /**
     * 读取resource目录下的文件
     *
     * @throws IOException
     */
    @Test
    public void main2 () throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resource/map.json"));
        String json = new String(bytes);

    }


    /**
     * json格式化输出
     * @throws IOException
     */
    @Test
    public void main3 () throws IOException {
        String str = "{\"orderAmount\": 1,\"orderDesc\": \"测试华配订单\",\"outBizNo\": \"hp00026\",\"payAmount\": 1,\"productName\": \"测试产品名称\",\"productShowUrl\": \"https://img2.tuhu.org/Images/Products/9921/9a7d/30d6cc10f356b1442a6925e8_w800_h800.png@60w_60h_100Q.png\"}";
        JSONObject jo = JSON.parseObject(str);

        User user = new User();
        user.setId("1");
        user.setName("lucy");
        user.setAge(18);

//        System.out.println(JSON.toJSONString(user));
//        System.out.println(JSON.toJSONString(user, true));

        System.out.println(user.toString());
        System.out.println(JSON.toJSONString(user));
        System.out.println(Format.print(user));
    }



}
