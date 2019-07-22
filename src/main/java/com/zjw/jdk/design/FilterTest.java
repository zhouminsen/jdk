package com.zjw.jdk.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoum on 2019-07-08.
 */
public class FilterTest {
    public static void main(String[] args) {
        Request request = new Request();
        Response response = new Response();
        FilterChain filterChain = new FilterChain();
        filterChain.add(new SecurityFilter()).add(new SSFilter());
        filterChain.doChain(request, response);
    }

}

class Request {
    void doSomething(String thing) {
        System.out.println(thing);
    }
}

class Response {
    void doSomething(String thing) {
        System.out.println(thing);
    }
}


interface Filter {
    void doFilter(Request request, Response response, FilterChain filterChain);
}

class SecurityFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.doSomething("权限执行");
        filterChain.doChain(request, response);
        response.doSomething("权限执行");

    }
}

class SSFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.doSomething("SS执行");
        filterChain.doChain(request, response);
        response.doSomething("SS执行");
    }
}

class FilterChain {
    private List<Filter> filterList = new ArrayList<>();

    private int index = 0;

    public FilterChain add(Filter filter) {
        filterList.add(filter);
        return this;
    }

    public void doChain(Request request, Response response) {
        if (filterList.size() == index) {
            return;
        }
        Filter filter = filterList.get(index);
        index++;
        filter.doFilter(request, response, this);
    }
}



