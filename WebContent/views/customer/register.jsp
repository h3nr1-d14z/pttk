<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Membership - CineMan</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=3">
</head>
<body>
    <div class="container">
        <div class="login-form-container" style="max-width: 600px;">
            <h1>Register Membership</h1>
            <p style="text-align: center; color: #7f8c8d; margin-bottom: 20px;">
                Join CineMan and enjoy exclusive benefits!
            </p>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <% if (request.getAttribute("success") != null) { %>
                <div class="alert alert-success">
                    <%= request.getAttribute("success") %>
                    <% if (request.getAttribute("cardNumber") != null) { %>
                        <br><strong>Your Membership Card Number: <%= request.getAttribute("cardNumber") %></strong>
                    <% } %>
                </div>
            <% } %>

            <form method="post" action="${pageContext.request.contextPath}/register" class="login-form">
                <h3>Account Information</h3>

                <div class="form-group">
                    <label for="username">Username: *</label>
                    <input type="text" id="username" name="username"
                           value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>"
                           required pattern="[a-zA-Z0-9]{3,20}"
                           title="3-20 characters, letters and numbers only">
                </div>

                <div class="form-group">
                    <label for="password">Password: *</label>
                    <input type="password" id="password" name="password"
                           required minlength="6"
                           title="Minimum 6 characters">
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Confirm Password: *</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>

                <h3>Personal Information</h3>

                <div class="form-group">
                    <label for="fullName">Full Name: *</label>
                    <input type="text" id="fullName" name="fullName"
                           value="<%= request.getAttribute("fullName") != null ? request.getAttribute("fullName") : "" %>"
                           required>
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number: *</label>
                    <input type="tel" id="phone" name="phone"
                           value="<%= request.getAttribute("phone") != null ? request.getAttribute("phone") : "" %>"
                           required pattern="[0-9]{10,11}"
                           title="10-11 digits">
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email"
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
                </div>

                <div class="form-group">
                    <label for="birthDate">Date of Birth:</label>
                    <input type="date" id="birthDate" name="birthDate"
                           value="<%= request.getAttribute("birthDate") != null ? request.getAttribute("birthDate") : "" %>">
                </div>

                <div class="form-group">
                    <label for="address">Address:</label>
                    <textarea id="address" name="address" rows="3"><%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %></textarea>
                </div>

                <div class="form-group">
                    <label>
                        <input type="checkbox" required>
                        I agree to terms and conditions *
                    </label>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Register Membership</button>
            </form>

            <div class="form-footer">
                <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
                <p><a href="${pageContext.request.contextPath}/">Back to Home</a></p>
            </div>

            <div style="margin-top: 20px; padding: 15px; background: #ecf0f1; border-radius: 5px;">
                <h4>Membership Benefits:</h4>
                <ul style="margin-left: 20px;">
                    <li>Special discounts on tickets</li>
                    <li>Priority booking</li>
                    <li>Earn points with every purchase</li>
                    <li>Exclusive member events</li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
