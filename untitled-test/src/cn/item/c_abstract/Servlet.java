package cn.item.c_abstract;

public interface Servlet {
    void init ();
    void service ();
    void destroy ();

    int sum () ;

    static String add (String s) {
        return s +" => add";
    };
}
