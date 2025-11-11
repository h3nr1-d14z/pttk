<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.cineman.model.MovieStatistics" %>
<%@ page import="com.cineman.model.ShowtimeStatistics" %>
<%@ page import="com.cineman.model.Invoice" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    currencyFormat.setMaximumFractionDigits(0);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    int level = (Integer) request.getAttribute("level");
    String startDate = (String) request.getAttribute("startDate");
    String endDate = (String) request.getAttribute("endDate");
    double totalRevenue = (Double) request.getAttribute("totalRevenue");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie Revenue Statistics - CineMan Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=3">
</head>
<body>
    <div class="container">
        <header>
            <h1>CineMan - Admin Panel</h1>
            <p>Welcome, <%= session.getAttribute("fullName") %>!</p>
        </header>

        <nav style="background: white; padding: 15px; margin-bottom: 20px; border-radius: 5px;">
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary">Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin/movie-statistics" class="btn btn-primary">Movie Statistics</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" style="float: right;">Logout</a>
        </nav>

        <main>
            <h2>Movie Revenue Statistics</h2>

            <!-- Date Range Filter -->
            <div style="background: white; padding: 15px; margin-bottom: 20px; border-radius: 5px;">
                <form method="get" action="${pageContext.request.contextPath}/admin/movie-statistics" style="display: flex; gap: 15px; align-items: end;">
                    <div class="form-group" style="margin: 0;">
                        <label for="startDate">From:</label>
                        <input type="date" id="startDate" name="startDate" value="<%= startDate %>">
                    </div>
                    <div class="form-group" style="margin: 0;">
                        <label for="endDate">To:</label>
                        <input type="date" id="endDate" name="endDate" value="<%= endDate %>">
                    </div>
                    <button type="submit" class="btn btn-primary">Filter</button>
                </form>
            </div>

            <!-- Navigation breadcrumb -->
            <div style="background: #ecf0f1; padding: 10px 15px; margin-bottom: 20px; border-radius: 5px;">
                <% if (level == 1) { %>
                    <strong>Level 1:</strong> Movie List
                <% } else if (level == 2) { %>
                    <a href="${pageContext.request.contextPath}/admin/movie-statistics?startDate=<%= startDate %>&endDate=<%= endDate %>">Level 1: Movie List</a>
                    → <strong>Level 2:</strong> Showtimes for "<%= request.getAttribute("movieTitle") %>"
                <% } else if (level == 3) { %>
                    <a href="${pageContext.request.contextPath}/admin/movie-statistics?startDate=<%= startDate %>&endDate=<%= endDate %>">Level 1: Movie List</a>
                    → <a href="javascript:history.back()">Level 2: Showtimes</a>
                    → <strong>Level 3:</strong> Invoices
                <% } %>
            </div>

            <!-- Level 1: Movie List -->
            <% if (level == 1) { %>
                <%
                    List<MovieStatistics> movieStats = (List<MovieStatistics>) request.getAttribute("movieStats");
                    if (movieStats != null && !movieStats.isEmpty()) {
                %>
                    <div style="background: white; padding: 15px; border-radius: 5px;">
                        <h3>Movies Ranked by Revenue</h3>
                        <p><strong>Total Revenue:</strong> <%= currencyFormat.format(totalRevenue) %> VND</p>
                        <table style="width: 100%; border-collapse: collapse; margin-top: 15px;">
                            <thead>
                                <tr style="background: #3498db; color: white;">
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">#</th>
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">Movie Title</th>
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">Genre</th>
                                    <th style="padding: 10px; text-align: right; border: 1px solid #ddd;">Tickets Sold</th>
                                    <th style="padding: 10px; text-align: right; border: 1px solid #ddd;">Revenue (VND)</th>
                                    <th style="padding: 10px; text-align: center; border: 1px solid #ddd;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int rank = 1;
                                    for (MovieStatistics stat : movieStats) {
                                %>
                                    <tr>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= rank++ %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= stat.getTitle() %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= stat.getGenre() %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd; text-align: right;"><%= stat.getTotalTickets() %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd; text-align: right;"><%= currencyFormat.format(stat.getTotalRevenue()) %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd; text-align: center;">
                                            <a href="${pageContext.request.contextPath}/admin/movie-statistics?movieId=<%= stat.getMovieId() %>&startDate=<%= startDate %>&endDate=<%= endDate %>"
                                               class="btn btn-primary" style="padding: 5px 10px; font-size: 14px;">View Showtimes</a>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                <% } else { %>
                    <p style="text-align: center; color: #7f8c8d;">No revenue data found for the selected date range.</p>
                <% } %>
            <% } %>

            <!-- Level 2: Showtime List for Selected Movie -->
            <% if (level == 2) { %>
                <%
                    List<ShowtimeStatistics> showtimeStats = (List<ShowtimeStatistics>) request.getAttribute("showtimeStats");
                    if (showtimeStats != null && !showtimeStats.isEmpty()) {
                %>
                    <div style="background: white; padding: 15px; border-radius: 5px;">
                        <h3>Showtimes for "<%= request.getAttribute("movieTitle") %>"</h3>
                        <p><strong>Total Revenue:</strong> <%= currencyFormat.format(totalRevenue) %> VND</p>
                        <table style="width: 100%; border-collapse: collapse; margin-top: 15px;">
                            <thead>
                                <tr style="background: #27ae60; color: white;">
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">#</th>
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">Showtime</th>
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">Room</th>
                                    <th style="padding: 10px; text-align: right; border: 1px solid #ddd;">Tickets Sold</th>
                                    <th style="padding: 10px; text-align: right; border: 1px solid #ddd;">Revenue (VND)</th>
                                    <th style="padding: 10px; text-align: center; border: 1px solid #ddd;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int rank = 1;
                                    for (ShowtimeStatistics stat : showtimeStats) {
                                %>
                                    <tr>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= rank++ %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= dateTimeFormat.format(stat.getShowtime()) %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= stat.getRoomName() %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd; text-align: right;"><%= stat.getTotalTickets() %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd; text-align: right;"><%= currencyFormat.format(stat.getTotalRevenue()) %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd; text-align: center;">
                                            <a href="${pageContext.request.contextPath}/admin/movie-statistics?showtimeId=<%= stat.getShowtimeId() %>"
                                               class="btn btn-primary" style="padding: 5px 10px; font-size: 14px;">View Invoices</a>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <div style="margin-top: 15px;">
                            <a href="${pageContext.request.contextPath}/admin/movie-statistics?startDate=<%= startDate %>&endDate=<%= endDate %>" class="btn btn-secondary">← Back to Movie List</a>
                        </div>
                    </div>
                <% } else { %>
                    <p style="text-align: center; color: #7f8c8d;">No showtime data found for this movie.</p>
                    <a href="${pageContext.request.contextPath}/admin/movie-statistics?startDate=<%= startDate %>&endDate=<%= endDate %>" class="btn btn-secondary">← Back to Movie List</a>
                <% } %>
            <% } %>

            <!-- Level 3: Invoice List for Selected Showtime -->
            <% if (level == 3) { %>
                <%
                    List<Invoice> invoices = (List<Invoice>) request.getAttribute("invoices");
                    if (invoices != null && !invoices.isEmpty()) {
                %>
                    <div style="background: white; padding: 15px; border-radius: 5px;">
                        <h3>Invoices for Showtime</h3>
                        <p><strong>Total Revenue:</strong> <%= currencyFormat.format(totalRevenue) %> VND</p>
                        <table style="width: 100%; border-collapse: collapse; margin-top: 15px;">
                            <thead>
                                <tr style="background: #e74c3c; color: white;">
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">#</th>
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">Invoice Number</th>
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">Date</th>
                                    <th style="padding: 10px; text-align: right; border: 1px solid #ddd;">Total Amount</th>
                                    <th style="padding: 10px; text-align: right; border: 1px solid #ddd;">Discount</th>
                                    <th style="padding: 10px; text-align: right; border: 1px solid #ddd;">Final Amount</th>
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">Payment</th>
                                    <th style="padding: 10px; text-align: left; border: 1px solid #ddd;">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int rank = 1;
                                    for (Invoice invoice : invoices) {
                                %>
                                    <tr>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= rank++ %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= invoice.getInvoiceNumber() %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= dateTimeFormat.format(invoice.getCreatedAt()) %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd; text-align: right;"><%= currencyFormat.format(invoice.getTotalAmount()) %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd; text-align: right;"><%= currencyFormat.format(invoice.getDiscount()) %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd; text-align: right;"><%= currencyFormat.format(invoice.getFinalAmount()) %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd;"><%= invoice.getPaymentMethod() %></td>
                                        <td style="padding: 10px; border: 1px solid #ddd;">
                                            <span style="padding: 3px 8px; border-radius: 3px; background: <%= "PAID".equals(invoice.getStatus()) ? "#27ae60" : "#e74c3c" %>; color: white; font-size: 12px;">
                                                <%= invoice.getStatus() %>
                                            </span>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <div style="margin-top: 15px;">
                            <a href="javascript:history.back()" class="btn btn-secondary">← Back to Showtimes</a>
                        </div>
                    </div>
                <% } else { %>
                    <p style="text-align: center; color: #7f8c8d;">No invoice data found for this showtime.</p>
                    <a href="javascript:history.back()" class="btn btn-secondary">← Back to Showtimes</a>
                <% } %>
            <% } %>
        </main>

        <footer>
            <p>&copy; 2024 CineMan. Admin Panel.</p>
        </footer>
    </div>
</body>
</html>
