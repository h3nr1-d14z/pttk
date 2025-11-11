package com.cineman.servlet;

import com.cineman.dao.CustomerDAO;
import com.cineman.dao.InvoiceDAO;
import com.cineman.dao.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Dashboard Servlet for Admin
 */
@WebServlet("/admin/dashboard")
public class DashboardServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private CustomerDAO customerDAO;
    private InvoiceDAO invoiceDAO;

    @Override
    public void init() throws ServletException {
        movieDAO = new MovieDAO();
        customerDAO = new CustomerDAO();
        invoiceDAO = new InvoiceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get statistics
        int totalMovies = movieDAO.getAllMovies().size();
        int totalCustomers = customerDAO.getAllCustomers().size();
        int totalMembers = customerDAO.getAllMembers().size();
        int totalInvoices = invoiceDAO.getAllInvoices().size();

        // Set attributes
        request.setAttribute("totalMovies", totalMovies);
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("totalMembers", totalMembers);
        request.setAttribute("totalInvoices", totalInvoices);

        // Forward to dashboard JSP
        request.getRequestDispatcher("/views/admin/dashboard.jsp").forward(request, response);
    }
}
