package com.first.abstracts;

public interface Servlet {
    void init();
    void service();
    void destroy();
    int sum() ;

    static String add(String s) {
        return s +" => add";
    };
}
