package com.accn.ppes.magellan.packageFilter;

 import org.springframework.stereotype.Component;

   import javax.servlet.*;
   import javax.servlet.http.HttpServletResponse;
   import java.io.IOException;

/**
 * Note this is a very simple CORS filter that is wide open.
 * This would need to be locked down.
 */
@Component
public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", " x-requested-with, content-type,owasp_csrftoken");
        //content-type,owasp_csrftoken,x-requested-with
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}