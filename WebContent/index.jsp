<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CineMan - Cinema Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=3">
</head>
<body>
    <div class="container">
        <header>
            <h1>Welcome to CineMan</h1>
            <p>Your Cinema Booking & Management System</p>
        </header>

        <main>
            <div class="welcome-section">
                <h2>Book Your Movie Tickets Online</h2>
                <p>Experience the latest movies in our state-of-the-art cinemas</p>

                <div class="action-buttons">
                    <%
                        if (session.getAttribute("user") != null) {
                            String role = (String) session.getAttribute("role");
                            if ("ADMIN".equals(role)) {
                    %>
                        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-primary">Go to Dashboard</a>
                    <%
                            } else if ("STAFF".equals(role)) {
                    %>
                        <a href="${pageContext.request.contextPath}/staff/sell-ticket" class="btn btn-primary">Sell Tickets</a>
                    <%
                            } else {
                    %>
                        <a href="${pageContext.request.contextPath}/movies" class="btn btn-primary">Browse Movies</a>
                    <%
                            }
                    %>
                        <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">Logout</a>
                    <%
                        } else {
                    %>
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Login</a>
                        <a href="${pageContext.request.contextPath}/register" class="btn btn-secondary">Register</a>
                        <a href="${pageContext.request.contextPath}/movies" class="btn btn-outline">Browse Movies</a>
                    <%
                        }
                    %>
                </div>
            </div>

            <div style="background: #d4edda; padding: 15px; margin: 20px 0; border-radius: 5px; border-left: 4px solid #28a745;">
                <h3 style="margin-top: 0; color: #155724;">✓ Implemented Features</h3>
                <ul style="color: #155724; margin: 10px 0;">
                    <li><strong>Customer Membership Registration</strong> - Register and receive a membership card</li>
                    <li><strong>Admin Movie Revenue Statistics</strong> - 3-level drill-down reports (Movies → Showtimes → Invoices)</li>
                </ul>
            </div>

            <div class="features">
                <h3>Why Choose CineMan?</h3>
                <div class="feature-grid">
                    <div class="feature-item">
                        <h4>Easy Online Booking</h4>
                        <p>Book your tickets anytime, anywhere</p>
                    </div>
                    <div class="feature-item">
                        <h4>Latest Movies</h4>
                        <p>Watch the newest releases</p>
                    </div>
                    <div class="feature-item">
                        <h4>Comfortable Seats</h4>
                        <p>2D, 3D, IMAX, and VIP options</p>
                    </div>
                    <div class="feature-item">
                        <h4>Membership Benefits</h4>
                        <p>Earn points and get discounts</p>
                    </div>
                </div>
            </div>
        </main>

        <footer>
            <p>&copy; 2024 CineMan. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
