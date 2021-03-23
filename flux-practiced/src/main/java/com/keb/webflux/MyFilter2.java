package com.keb.webflux;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter2 implements Filter {

    private EventNotify eventNotify;

    public MyFilter2(EventNotify eventNotify) {
        this.eventNotify = eventNotify;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터 2 실행됨 !!");
        //데이터를 하나 발생시킴
        eventNotify.add("new data here");
    }
}
