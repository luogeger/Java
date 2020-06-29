package com.first.argument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author
 */
public class Main {


    public static void main(String[] args) {
//        List<String> a = Arrays.asList("a");
//        List<String> b = Arrays.asList("b");
//        List<String> c = Arrays.asList("c");


        List<String> a = new ArrayList(); a.add("a");
        List<String> b = new ArrayList(); b.add("b");
        List<String> c = new ArrayList(); c.add("c");


        function(a, b, c);
    }

    /**
     *
     * @param a
     * @param args 可变参数必须在最后一个
     */
    private static void function(List<String> a, List<String>... args){
        /**
         *
         * length是一样的
         *  relevanceList = [protocol, method, response]
         *  args[0] = [protocolCode, methodCode, responseCode]
         *  args[1] = [protocolName, methodName, responseName]
         *
         *  遍历 relevanceList 获取对应Field值
         *      idMap => id1 -> "protocol"
         *      idMap => id2 -> "method"
         *      idMap => id3 -> "response"
         *
         *  读DB
         *      dictList = baseMapper.select(new ArrayList(idMap.keys))
         *
         *  dictList转换dictMap
         *      id1 -> dict{code, name},
         *      id2 -> dict{code, name},
         *      id3 -> dict{code, name},
         *
         *  还是遍历 relevanceList，获取assignment对应Filed，
         *      args[0].get(i).toLowerCase.equals("code") { .getCode() }
         *      args[1].get(i).toLowerCase.equals("name") { .getName() }
         *
         */

        int len = args.length;
        HashMap<Integer, String> assignmentMap = new HashMap<>(len);

        System.out.println(123);
    }
}
