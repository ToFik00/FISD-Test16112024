package org.piva.fisd.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class CharsetFilter implements Filter {

    private static final String DEFAULT_ENCODING = "UTF-8";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getCharacterEncoding() == null) {
            httpRequest.setCharacterEncoding(DEFAULT_ENCODING);
        }
        httpResponse.setCharacterEncoding(DEFAULT_ENCODING);
        httpResponse.setContentType("text/html; charset=" + DEFAULT_ENCODING);

        chain.doFilter(request, response);
    }

}