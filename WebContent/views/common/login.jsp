<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - CineMan</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=3">
</head>
<body>
    <div class="container">
        <div class="login-form-container">
            <h1>CineMan Login</h1>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getParameter("error") %>
                </div>
            <% } %>

            <% if (request.getAttribute("success") != null) { %>
                <div class="alert alert-success">
                    <%= request.getAttribute("success") %>
                </div>
            <% } %>

            <% if (request.getParameter("message") != null) { %>
                <div class="alert alert-info">
                    <%= request.getParameter("message") %>
                </div>
            <% } %>

            <form method="post" action="${pageContext.request.contextPath}/login" class="login-form">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required autofocus>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Login</button>
            </form>

            <div class="form-footer">
                <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a></p>
                <p><a href="${pageContext.request.contextPath}/">Back to Home</a></p>
            </div>

            <div class="demo-accounts">
                <h3>Demo Accounts</h3>
                <table>
                    <tr>
                        <th>Role</th>
                        <th>Username</th>
                        <th>Password</th>
                    </tr>
                    <tr>
                        <td>Admin</td>
                        <td>admin</td>
                        <td>123456</td>
                    </tr>
                    <tr>
                        <td>Staff</td>
                        <td>staff1</td>
                        <td>123456</td>
                    </tr>
                    <tr>
                        <td>Customer</td>
                        <td>customer1</td>
                        <td>123456</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
