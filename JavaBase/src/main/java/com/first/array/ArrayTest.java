package com.first.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoxiaoqing
 * @date 2020-01-31__13:02
 */
public class ArrayTest {
    @Test
    public void main1 () {
        List<String> list = new ArrayList<String>() {{
            add("2");
            add("3");
            add("3");
            add("3");
            add("4");
        }};
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("3")) {
                list.remove(i);
            }
        }
        System.out.println(list.toString());

        /**
         * list.remove(i)
         *
         *
         * Suspicious 'List.remove()' in the loop less...
         * (Ctrl+F1) Inspection info:
         * Reports when list.remove(index) is called inside the ascending counted loop.
         * This is suspicious as list becomes shorter after that and the element next to removed will not be processed.
         * Simple fix is to decrease the index variable after removal,
         * but probably removing via iterator or using removeIf method (since Java 8) is a more robust alternative.
         * If you don't expect that remove will be called more than once in a loop,
         * consider adding a break command after it.
         *
         * 较少的循环中有可疑的“ List.remove（）” ...
         * （Ctrl + F1）检查信息：
         * 报告在递增计数循环内何时调用list.remove（index）。
         * 这很可疑，因为列表在此之后变得更短，并且移除后的元素将不被处理。
         * 一个简单的解决方法是在删除后减少index变量，
         * 但是可能通过迭代器或使用removeIf方法（由于Java 8）删除是一个更可靠的选择。
         * 如果您不希望remove在循环中被多次调用，
         * 请考虑在其后添加break命令。
         */
    }
}
