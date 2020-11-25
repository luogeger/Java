package com.first.string;

public class StringTool {


    /**
     * 比较两个字符串的大小
     * <pre>
     *     str1 > str2 == 1;
     *     str1 < str2 == -1;
     *     str1 == str2 == 0;
     * </pre>
     * @param str1
     * @param str2
     * @return
     */
    public static int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }

}
