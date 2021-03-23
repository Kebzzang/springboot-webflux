package com.keb.webflux;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter implements Filter {

    private EventNotify eventNotify;

    public MyFilter(EventNotify eventNotify) {
        this.eventNotify = eventNotify;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터 실행됨");
        HttpServletResponse servletResponse=(HttpServletResponse) response;
        servletResponse.setContentType("text/event-stream; charset=utf-8");
        PrintWriter out=servletResponse.getWriter();
        for ( int i=0; i<5; i++){
            out.print("응답 : "+ i+"\n");
            out.flush(); //버퍼를 비우기
            try {
                Thread.sleep(1000); //1초마다 딜레이
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(true){
            try {
                if(eventNotify.getChange()){
                    int lastindex=eventNotify.getEvents().size()-1;
                    out.print("응답 : "+eventNotify.getEvents().get(lastindex)+"\n");
                    out.flush();
                    eventNotify.setChange(false);
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
