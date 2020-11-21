package com.first.design.pattern.behavior.chain.of.responsibility.a;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    @Test
    public void main () {

        Message message = new Message(":) <script> 大家好! 今天继续996! </script>");

        FilterChain fc = new FilterChain();
        fc.add(new HtmlFilter());
        fc.add(new SensitiveFilter());
        fc.doFilter(message);

        System.out.println(message.msg);

    }


}




class FilterChain {

    private List<Filter> filters = new ArrayList<>();

    void doFilter(Message msg) {
        for (Filter filter : filters) {
            filter.doFilter(msg);
        }
    }

    public void add(Filter filter) {
        filters.add(filter);
    }
}

class HtmlFilter implements Filter{

    @Override
    public boolean doFilter(Message msg) {
        msg.msg = msg.msg.replaceAll("<", "[").replaceAll(">", "]");
        return true;
    }
}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter(Message msg) {
        msg.msg = msg.msg.replaceAll("996", "007");
        return true;
    }
}

interface Filter {
    boolean doFilter(Message msg);
}

class Message {
    String  msg;

    public Message(String s) {
        this.msg = s;
    }
}