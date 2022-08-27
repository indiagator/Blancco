package com.testblancco.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "TestFilter")
public class TestFilter implements Filter
{
    public void init(FilterConfig config) throws ServletException
    {
    }

    public void destroy()
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {

        // incoming part
        request.setAttribute("filterParam",new Integer(67));

        chain.doFilter(request, response); // divider between incoming request processing & outgoing response processing

        // outgoing part
    }
}
