package com.example.demo;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        chain.doFilter(req, res);
        /*try {
            chain.doFilter(req, res);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Aborted by user!");
        }*/
        System.out.println(((HttpServletRequest)req).getRemoteAddr());
        System.out.println(((HttpServletRequest)req).getRequestURI());
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}

}
