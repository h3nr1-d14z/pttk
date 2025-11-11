<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - CineMan</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=3">
</head>
<body>
    <div class="container">
        <header>
            <h1>CineMan - Admin Dashboard</h1>
            <p>Welcome, <%= session.getAttribute("fullName") %>!</p>
        </header>

        <nav style="background: white; padding: 15px; margin-bottom: 20px; border-radius: 5px;">
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-primary">Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin/movie-statistics" class="btn btn-secondary">Movie Statistics</a>
            <a href="${pageContext.request.contextPath}/movies" class="btn btn-secondary">Movies</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" style="float: right;">Logout</a>
        </nav>

        <main>
            <h2>System Statistics</h2>

            <div class="feature-grid">
                <div class="feature-item" style="background: #3498db; color: white;">
                    <h3 style="color: white; font-size: 48px; margin: 10px 0;">${totalMovies}</h3>
                    <h4 style="color: white;">Total Movies</h4>
                    <p style="color: white;">Movies in system</p>
                </div>

                <div class="feature-item" style="background: #2ecc71; color: white;">
                    <h3 style="color: white; font-size: 48px; margin: 10px 0;">${totalCustomers}</h3>
                    <h4 style="color: white;">Total Customers</h4>
                    <p style="color: white;">Registered customers</p>
                </div>

                <div class="feature-item" style="background: #f39c12; color: white;">
                    <h3 style="color: white; font-size: 48px; margin: 10px 0;">${totalMembers}</h3>
                    <h4 style="color: white;">Members</h4>
                    <p style="color: white;">Active memberships</p>
                </div>

                <div class="feature-item" style="background: #e74c3c; color: white;">
                    <h3 style="color: white; font-size: 48px; margin: 10px 0;">${totalInvoices}</h3>
                    <h4 style="color: white;">Total Invoices</h4>
                    <p style="color: white;">All transactions</p>
                </div>
            </div>

            <div style="margin-top: 30px; background: white; padding: 20px; border-radius: 5px;">
                <h3>Quick Actions</h3>
                <div style="margin-top: 20px;">
                    <a href="${pageContext.request.contextPath}/admin/movie-statistics" class="btn btn-success">Movie Revenue Statistics</a>
                    <a href="${pageContext.request.contextPath}/admin/movies" class="btn btn-secondary" disabled title="Coming soon">Manage Movies</a>
                    <a href="${pageContext.request.contextPath}/admin/rooms" class="btn btn-secondary" disabled title="Coming soon">Manage Rooms</a>
                    <a href="${pageContext.request.contextPath}/admin/showtimes" class="btn btn-secondary" disabled title="Coming soon">Schedule Showtimes</a>
                </div>
            </div>

            <div style="margin-top: 30px; background: #ecf0f1; padding: 20px; border-radius: 5px;">
                <h3>System Information</h3>
                <table>
                    <tr>
                        <td><strong>User:</strong></td>
                        <td><%= session.getAttribute("fullName") %></td>
                    </tr>
                    <tr>
                        <td><strong>Role:</strong></td>
                        <td><%= session.getAttribute("role") %></td>
                    </tr>
                    <tr>
                        <td><strong>Username:</strong></td>
                        <td><%= session.getAttribute("username") %></td>
                    </tr>
                    <tr>
                        <td><strong>Session ID:</strong></td>
                        <td><%= session.getId() %></td>
                    </tr>
                </table>
            </div>
        </main>

        <footer>
            <p>&copy; 2024 CineMan. Admin Panel.</p>
        </footer>
    </div>
</body>
</html>
