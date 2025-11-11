package com.cineman.servlet;

import com.cineman.dao.UserDAO;
import com.cineman.model.User;
import com.cineman.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for user login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to login page
        request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set character encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Get parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input
        if (ValidationUtil.isEmpty(username) || ValidationUtil.isEmpty(password)) {
            request.setAttribute("error", "Username and password are required");
            request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
            return;
        }

        // Authenticate user
        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            // Login successful
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            session.setAttribute("fullName", user.getFullName());

            // Redirect based on role
            String redirectUrl = getRedirectUrlByRole(user.getRole());
            response.sendRedirect(request.getContextPath() + redirectUrl);
        } else {
            // Login failed
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
        }
    }

    /**
     * Get redirect URL based on user role
     */
    private String getRedirectUrlByRole(String role) {
        switch (role) {
            case "ADMIN":
                return "/admin/dashboard";
            case "STAFF":
                return "/staff/sell-ticket";
            case "CUSTOMER":
                return "/customer/home";
            default:
                return "/";
        }
    }
}
