package cn.everythinggrows.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionValidateFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        SysContent.setRequest((HttpServletRequest) request);
        SysContent.setResponse((HttpServletResponse) response);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
