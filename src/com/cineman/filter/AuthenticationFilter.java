package com.cineman.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter to check if user is authenticated
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        // Check if user is logged in
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (!isLoggedIn) {
            // Not logged in, redirect to login
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }

        // Check role-based access
        String role = (String) session.getAttribute("role");

        if (requestURI.startsWith(contextPath + "/admin/") && !"ADMIN".equals(role)) {
            // Trying to access admin page without ADMIN role
            httpResponse.sendRedirect(contextPath + "/login?error=Access denied");
            return;
        }

        if (requestURI.startsWith(contextPath + "/staff/") && !"STAFF".equals(role) && !"ADMIN".equals(role)) {
            // Trying to access staff page without STAFF or ADMIN role
            httpResponse.sendRedirect(contextPath + "/login?error=Access denied");
            return;
        }

        if (requestURI.startsWith(contextPath + "/customer/") && !"CUSTOMER".equals(role) && !"ADMIN".equals(role)) {
            // Trying to access customer page without CUSTOMER or ADMIN role
            httpResponse.sendRedirect(contextPath + "/login?error=Access denied");
            return;
        }

        // User is authenticated and has correct role, continue
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
