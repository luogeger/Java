package com.first.design.pattern.behavior.chain.of.responsibility.b;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class Main {
    @Test
    public void main() {

        Request request = new Request(":) <script> 大家好! 今天继续996! </script>");
        Response response = new Response("response ");

        FilterChain fc = new FilterChain();
        fc.add(new HtmlFilter());
        fc.add(new SensitiveFilter());
        fc.doFilter(request, response);

        System.out.println(request.msg);
        System.out.println(response.msg);

    }


}


class FilterChain {

    private List<Filter> filters = new ArrayList<>();

    void doFilter(Request request, Response response) {
        for (Filter filter : filters) {
            boolean boo = filter.doFilter(request, response);
            if (!boo) {
                break;
            }
        }


    }

    public void add(Filter filter) {
        filters.add(filter);
    }
}

class HtmlFilter implements Filter {

    @Override
    public boolean doFilter(Request request, Response response) {
        request.msg = request.msg.replaceAll("<", "[").replaceAll(">", "]");
        //  不在此时处理response
        response.msg = response.msg + " + html";
        return true;
    }
}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter(Request request, Response response) {
        request.msg = request.msg.replaceAll("996", "007");
        response.msg = response.msg + " + sensitive";
        return true;
    }
}

interface Filter {
    boolean doFilter(Request request, Response response);
}

class Request {
    String msg;

    public Request(String s) {
        this.msg = s;
    }
}

class Response {
    String msg;

    public Response(String msg) {
        this.msg = msg;
    }
}