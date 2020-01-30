package com.first.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Chars;
import org.junit.Test;

/**
 * guava新集合类型
 *
 * @author luoxiaoqing
 * @date 2018-01-13__19:45
 */
public class MultisetTest {

    private static final String text =
            "《南陵别儿童入京》" +
                    "白酒新熟山中归，黄鸡啄黍秋正肥。" +
                    "呼童烹鸡酌白酒，儿女嬉笑牵人衣。" +
                    "高歌取醉欲自慰，起舞落日争光辉。" +
                    "游说万乘苦不早，著鞭跨马涉远道。" +
                    "会稽愚妇轻买臣，余亦辞家西入秦。" +
                    "仰天大笑出门去，我辈岂是蓬蒿人。";


    /**
     * 1.不连续的ArrayList
     *      add: 添加单个元素
     *      iterator: 返回所有元素（包括重复元素）
     *      size: 返回所有元素的总个数（包括重复元素）
     * 2.键为元素，值为计数的Map
     *      count: 返回给定元素的计数
     *      entrySet：和Map的entrySet类似
     *      elementSet：返回所有不重复元素的Set，和Map的keySet类似
     */
    @Test
    public void handle() {
        // multiset创建
        Multiset<Character> multiset = HashMultiset.create();

        // string 转换成 char 数组
        char[] chars = text.toCharArray();

        // 遍历数组，添加到multiset中
        Chars.asList(chars)
                .stream()
                .forEach(charItem -> {
                    multiset.add(charItem);
                });

        System.out.println("size : " + multiset.size());
        System.out.println("count : " + multiset.count('人'));

    }
}
