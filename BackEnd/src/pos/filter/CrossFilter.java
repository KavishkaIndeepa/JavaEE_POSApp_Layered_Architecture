package pos.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
@WebFilter(urlPatterns = "/*" ,filterName = "CrossFilter")
*/
public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CORS init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
  HttpServletRequest req = (HttpServletRequest) servletRequest;
;
        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        System.out.println("crossfilter");
            res.setStatus(200);
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.addHeader("Access-Control-Allow-Methods", "PUT, DELETE");
            res.addHeader("Access-Control-Allow-Headers", "content-type,auth");
       res.setContentType("application/json");

    }

    @Override
    public void destroy() {

    }
}
