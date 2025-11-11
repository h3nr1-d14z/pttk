package com.cineman.servlet;

import com.cineman.dao.MovieDAO;
import com.cineman.model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Home page for Customer
 */
@WebServlet("/customer/home")
public class CustomerHomeServlet extends HttpServlet {
    private MovieDAO movieDAO;

    @Override
    public void init() throws ServletException {
        movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get now showing movies
        List<Movie> nowShowingMovies = movieDAO.getNowShowingMovies();
        List<Movie> comingSoonMovies = movieDAO.getComingSoonMovies();

        request.setAttribute("nowShowingMovies", nowShowingMovies);
        request.setAttribute("comingSoonMovies", comingSoonMovies);

        // Forward to customer home JSP
        request.getRequestDispatcher("/views/customer/home.jsp").forward(request, response);
    }
}
