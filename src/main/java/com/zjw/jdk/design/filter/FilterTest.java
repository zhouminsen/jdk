package com.zjw.jdk.design.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterTest {

	// main方法模拟Tomcat，假设现在浏览器有一个请求过来
    public static void main(String[] args) {
		// Tomcat准备了Request、Response
		Request request = new Request();
		Response response = new Response();

		// 过滤器链
		FilterChain filterChain = new FilterChain();
		// 注册过滤器，可以看看下方FilterChain代码是如何实现链式调用的
		filterChain.addFilter(new HTMLFilter()).addFilter(new SensitiveFilter());
		// 开始执行过滤器
		filterChain.doFilter(request, response);
	}

}

class Request {
	public void doSomething(String job) {
		System.out.println(job);
	}
}

class Response {
	public void doSomething(String job) {
		System.out.println(job);
	}
}

// 过滤器接口
interface Filter {
	void doFilter(Request request, Response response, FilterChain chain);
}


class HTMLFilter implements Filter {
	public void doFilter(Request request, Response response, FilterChain chain) {
		request.doSomething("HTMLFilter Request");
		chain.doFilter(request, response);
		response.doSomething("HTMLFilter Response");
	}
}

class SensitiveFilter implements Filter {
	public void doFilter(Request request, Response response, FilterChain chain) {
		request.doSomething("SensitiveFilter Request");
		chain.doFilter(request, response);
		response.doSomething("Sensitive Response");
	}
}

// 过滤器链
class FilterChain {

	// 标识当前执行到第几个过滤器
	private int index = 0;

	// 所有已注册的过滤器
	private List<Filter> filters = new ArrayList<Filter>();

	public FilterChain addFilter(Filter filter) {
		filters.add(filter);
                // return this，返回当前对象，即可形成链式调用
		return this;
	}

	// 执行过滤器
	public void doFilter(Request request, Response response) {
		// 所有过滤器执行完毕，return
		if (index == filters.size()) {
			return;
		}
		// 得到过滤器
		Filter filter = filters.get(index);
		// 自增操作不能和下面doFilter互换
		index++;
		// 执行过滤器
		filter.doFilter(request, response, this);
	}
}