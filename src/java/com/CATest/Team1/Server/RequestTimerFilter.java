package com.CATest.Team1.Server;

import java.io.*;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
// Ref : http://www.ntu.edu.sg/home/ehchua/programming/java/JavaServlets.html#zz-13.3 
@WebFilter(urlPatterns={"/*"})
public class RequestTimerFilter implements Filter {
   private static final Logger logger
           = Logger.getLogger(RequestTimerFilter.class.getName());
 
   @Override
   public void init(FilterConfig config) throws ServletException {
      logger.info("RequestTimerFilter initialized");
   }
 
   @Override
   public void doFilter(ServletRequest request, ServletResponse response,
           FilterChain chain)
           throws IOException, ServletException {
      long before = System.currentTimeMillis();
      chain.doFilter(request, response);
      long after = System.currentTimeMillis();
      String path = ((HttpServletRequest)request).getRequestURI();
      logger.info(path + ": " + (after - before) + " msec");
   }
 
   @Override
   public void destroy() {
      logger.info("RequestTimerFilter destroyed");
   }
}