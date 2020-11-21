package com.first.design.pattern.behavior.chain.of.responsibility.c;


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
        fc.add(new EnhanceFilter());
        fc.doFilter(request, response, fc);

        System.out.println(request.msg);
        System.out.println(response.msg);

    }


}

/**
 *
 */
class FilterChain {

    private List<Filter> filters = new ArrayList<>();
    private int index = 0;

    public void add(Filter filter) {
        filters.add(filter);
    }

    /**
     * 这里就不是用for循环去实现了，有点类似递归
     *
     * @param request
     * @param response
     * @param fc
     */
    void doFilter(Request request, Response response, FilterChain fc) {
        int size = filters.size();
        if (size == 0 || size == index) {
            return;
        }

        Filter filter = fc.filters.get(index);
        index++;
        filter.doFilter(request, response, fc);

    }
}

/**
 *
 */
class HtmlFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain fc) {
        request.msg = request.msg.replaceAll("<", "[").replaceAll(">", "]");
        fc.doFilter(request, response, fc);
        response.msg = response.msg + " + html";
    }
}

/**
 *
 */
class SensitiveFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain fc) {
        request.msg = request.msg.replaceAll("996", "007");
        fc.doFilter(request, response, fc);
        response.msg = response.msg + " + sensitive";
    }
}


/**
 *
 */
class EnhanceFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain fc) {
        request.msg = request.msg + " + enhance";
        fc.doFilter(request, response, fc);
        response.msg = response.msg + " + enhance";
    }
}


/**
 *
 */
interface Filter {
    void doFilter(Request request, Response response, FilterChain fc);
}

class Request {
    String msg;

    Request(String s) {
        this.msg = s;
    }
}

class Response {
    String msg;

    Response(String msg) {
        this.msg = msg;
    }
}