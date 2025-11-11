<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sell Ticket - CineMan Staff</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=3">
</head>
<body>
    <div class="container">
        <header>
            <h1>CineMan - Staff Panel</h1>
            <p>Welcome, <%= session.getAttribute("fullName") %>!</p>
        </header>

        <nav style="background: white; padding: 15px; margin-bottom: 20px; border-radius: 5px;">
            <a href="${pageContext.request.contextPath}/staff/sell-ticket" class="btn btn-primary">Sell Tickets</a>
            <a href="${pageContext.request.contextPath}/movies" class="btn btn-secondary">View Movies</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" style="float: right;">Logout</a>
        </nav>

        <main>
            <h2>Ticket Sales Counter</h2>

            <div style="background: #fff3cd; padding: 15px; margin-bottom: 20px; border-radius: 5px; border-left: 4px solid #ffc107;">
                <p style="margin: 0; color: #856404;">
                    <strong>Note:</strong> The features below are mock buttons for demonstration. Only the completed features are functional.
                </p>
            </div>

            <div style="background: white; padding: 20px; border-radius: 5px;">
                <h3>Ticket Sales</h3>
                <div style="margin-top: 20px; display: grid; gap: 15px;">
                    <button class="btn btn-secondary" disabled style="opacity: 0.5; cursor: not-allowed;" title="Feature not implemented">
                        🎫 Sell Tickets at Counter
                    </button>
                    <button class="btn btn-secondary" disabled style="opacity: 0.5; cursor: not-allowed;" title="Feature not implemented">
                        💳 Issue Membership Cards
                    </button>
                    <button class="btn btn-secondary" disabled style="opacity: 0.5; cursor: not-allowed;" title="Feature not implemented">
                        💰 Process Payments
                    </button>
                    <button class="btn btn-secondary" disabled style="opacity: 0.5; cursor: not-allowed;" title="Feature not implemented">
                        🖨️ Print Tickets
                    </button>
                </div>
            </div>

            <div style="background: white; padding: 20px; border-radius: 5px; margin-top: 20px;">
                <h3>Reports & Analytics</h3>
                <div style="margin-top: 20px; display: grid; gap: 15px;">
                    <button class="btn btn-secondary" disabled style="opacity: 0.5; cursor: not-allowed;" title="Feature not implemented">
                        📊 Daily Sales Report
                    </button>
                    <button class="btn btn-secondary" disabled style="opacity: 0.5; cursor: not-allowed;" title="Feature not implemented">
                        📈 View Sales Statistics
                    </button>
                </div>
            </div>

            <div style="margin-top: 30px; background: #ecf0f1; padding: 20px; border-radius: 5px;">
                <h3>Current User Info</h3>
                <table>
                    <tr>
                        <td><strong>Name:</strong></td>
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
                </table>
            </div>
        </main>

        <footer>
            <p>&copy; 2024 CineMan. Staff Panel.</p>
        </footer>
    </div>
</body>
</html>
