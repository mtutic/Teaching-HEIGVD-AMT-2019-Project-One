package ch.heigvd.amt.presentation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    // Paths to be ignored by the filter
    private static final Set ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("/login",
            "/logout", "/register")));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        // Check if the user id is stored in session
        if (request.getSession().getAttribute("user") != null || ALLOWED_PATHS.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // The user is not connected, redirect to the login page
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
