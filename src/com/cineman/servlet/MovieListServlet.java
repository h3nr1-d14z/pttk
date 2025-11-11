package com.cineman.servlet;

import com.cineman.dao.MovieDAO;
import com.cineman.model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to display movie list
 */
@WebServlet("/movies")
public class MovieListServlet extends HttpServlet {
    private MovieDAO movieDAO;

    @Override
    public void init() throws ServletException {
        movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get filter parameter
        String status = request.getParameter("status");
        String search = request.getParameter("search");

        List<Movie> movies;

        if (search != null && !search.trim().isEmpty()) {
            // Search movies
            movies = movieDAO.searchMoviesByTitle(search);
        } else if (status != null && !status.isEmpty()) {
            // Filter by status
            movies = movieDAO.getMoviesByStatus(status);
        } else {
            // Get all movies
            movies = movieDAO.getAllMovies();
        }

        // Set attributes
        request.setAttribute("movies", movies);
        request.setAttribute("selectedStatus", status);
        request.setAttribute("searchKeyword", search);

        // Forward to JSP
        request.getRequestDispatcher("/views/customer/movie-list.jsp").forward(request, response);
    }
}
