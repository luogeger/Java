package com.company.a_string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class T01 {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<String>();;
        me01(coll);
        System.out.println("----------------");
        me02(coll);
        System.out.println("------------------");
        me03(coll);

    }

    // forEach
    private static void me03(Collection<String> coll) {
        coll.add("qwer");
        coll.add("sadfdasf");
        coll.add("sdafgsdgret");
        coll.add("dfsghsdfhgdfhfgd");
        System.out.println(coll);
        for (String x : coll) {
            System.out.println(x);
        }
        coll.clear();
    }

    // 开发推荐
    private static void me02(Collection coll) {
        coll.add("sdlkfj");
        coll.add("wqeruio");
        coll.add("zm,xcnvb");
        coll.add("ouizxcv");
        System.out.println(coll);
        /*for (int i = 0; i < coll.size(); i++) {
            //System.out.println(coll.get(i));
        }*/

        for (Iterator it = coll.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        coll.clear();
    }

    // 使用迭代器
    private static void me01(Collection coll) {
        coll.add("sdf");
        coll.add("qwe");
        coll.add("rty");
        coll.add("cvb");
        System.out.println(coll);

        Iterator<String> it = coll.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        coll.clear();
    }



}
