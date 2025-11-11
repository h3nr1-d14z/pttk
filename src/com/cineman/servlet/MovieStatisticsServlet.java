package com.cineman.servlet;

import com.cineman.dao.InvoiceDAO;
import com.cineman.dao.MovieDAO;
import com.cineman.dao.StatisticsDAO;
import com.cineman.model.Invoice;
import com.cineman.model.Movie;
import com.cineman.model.MovieStatistics;
import com.cineman.model.ShowtimeStatistics;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Servlet for movie revenue statistics with 3-level drill-down
 * Level 1: Movie list with revenue
 * Level 2: Showtimes for selected movie with revenue
 * Level 3: Invoices for selected showtime
 */
@WebServlet("/admin/movie-statistics")
public class MovieStatisticsServlet extends HttpServlet {
    private StatisticsDAO statisticsDAO;
    private InvoiceDAO invoiceDAO;
    private MovieDAO movieDAO;

    @Override
    public void init() throws ServletException {
        statisticsDAO = new StatisticsDAO();
        invoiceDAO = new InvoiceDAO();
        movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get date range parameters (default: last 30 days)
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        Date startDate;
        Date endDate;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = new Date(sdf.parse(startDateStr).getTime());
            } else {
                // Default: 30 days ago
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, -30);
                startDate = new Date(cal.getTimeInMillis());
            }

            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = new Date(sdf.parse(endDateStr).getTime());
            } else {
                // Default: today
                endDate = new Date(System.currentTimeMillis());
            }

        } catch (Exception e) {
            // If date parsing fails, use default
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, -30);
            startDate = new Date(cal.getTimeInMillis());
            endDate = new Date(System.currentTimeMillis());
        }

        // Set date range for display
        request.setAttribute("startDate", startDate.toString());
        request.setAttribute("endDate", endDate.toString());

        // Check drill-down level
        String showtimeIdStr = request.getParameter("showtimeId");
        String movieIdStr = request.getParameter("movieId");

        if (showtimeIdStr != null && !showtimeIdStr.isEmpty()) {
            // Level 3: Show invoices for selected showtime
            handleLevel3(request, response, showtimeIdStr);
        } else if (movieIdStr != null && !movieIdStr.isEmpty()) {
            // Level 2: Show showtimes for selected movie
            handleLevel2(request, response, movieIdStr, startDate, endDate);
        } else {
            // Level 1: Show movie list with revenue
            handleLevel1(request, response, startDate, endDate);
        }
    }

    /**
     * Level 1: Show movie list with revenue
     */
    private void handleLevel1(HttpServletRequest request, HttpServletResponse response,
                              Date startDate, Date endDate) throws ServletException, IOException {
        List<MovieStatistics> movieStats = statisticsDAO.getMovieRevenueStatistics(startDate, endDate);

        // Calculate total revenue
        double totalRevenue = movieStats.stream()
                .mapToDouble(MovieStatistics::getTotalRevenue)
                .sum();

        request.setAttribute("level", 1);
        request.setAttribute("movieStats", movieStats);
        request.setAttribute("totalRevenue", totalRevenue);
        request.getRequestDispatcher("/views/admin/movie-statistics.jsp").forward(request, response);
    }

    /**
     * Level 2: Show showtimes for selected movie
     */
    private void handleLevel2(HttpServletRequest request, HttpServletResponse response,
                              String movieIdStr, Date startDate, Date endDate)
            throws ServletException, IOException {
        try {
            int movieId = Integer.parseInt(movieIdStr);

            // Get movie title from database
            Movie movie = movieDAO.getMovieById(movieId);
            String movieTitle = (movie != null) ? movie.getTitle() : "Unknown Movie";

            List<ShowtimeStatistics> showtimeStats = statisticsDAO.getShowtimeRevenueByMovie(movieId, startDate, endDate);

            // Calculate total revenue for this movie
            double totalRevenue = showtimeStats.stream()
                    .mapToDouble(ShowtimeStatistics::getTotalRevenue)
                    .sum();

            request.setAttribute("level", 2);
            request.setAttribute("movieId", movieId);
            request.setAttribute("movieTitle", movieTitle);
            request.setAttribute("showtimeStats", showtimeStats);
            request.setAttribute("totalRevenue", totalRevenue);
            request.getRequestDispatcher("/views/admin/movie-statistics.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/movie-statistics");
        }
    }

    /**
     * Level 3: Show invoices for selected showtime
     */
    private void handleLevel3(HttpServletRequest request, HttpServletResponse response,
                              String showtimeIdStr) throws ServletException, IOException {
        try {
            int showtimeId = Integer.parseInt(showtimeIdStr);
            List<Invoice> invoices = invoiceDAO.getInvoicesByShowtimeId(showtimeId);

            // Calculate total revenue for this showtime
            double totalRevenue = invoices.stream()
                    .filter(inv -> "PAID".equals(inv.getStatus()))
                    .mapToDouble(Invoice::getFinalAmount)
                    .sum();

            request.setAttribute("level", 3);
            request.setAttribute("showtimeId", showtimeId);
            request.setAttribute("invoices", invoices);
            request.setAttribute("totalRevenue", totalRevenue);
            request.getRequestDispatcher("/views/admin/movie-statistics.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/movie-statistics");
        }
    }
}
