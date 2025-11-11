<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.cineman.model.Movie" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - CineMan</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=3">
</head>
<body>
    <div class="container">
        <header>
            <h1>Welcome to CineMan</h1>
            <p>Hello, <%= session.getAttribute("fullName") %>!</p>
        </header>

        <nav style="background: white; padding: 15px; margin-bottom: 20px; border-radius: 5px;">
            <a href="${pageContext.request.contextPath}/customer/home" class="btn btn-primary">Home</a>
            <a href="${pageContext.request.contextPath}/movies" class="btn btn-secondary">All Movies</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" style="float: right;">Logout</a>
        </nav>

        <div style="background: #fff3cd; padding: 15px; margin-bottom: 20px; border-radius: 5px; border-left: 4px solid #ffc107;">
            <p style="margin: 0; color: #856404;">
                <strong>Note:</strong> Online booking feature is not yet implemented. "Book Now" buttons are disabled for demonstration.
            </p>
        </div>

        <main>
            <h2>Now Showing</h2>
            <%
                List<Movie> nowShowingMovies = (List<Movie>) request.getAttribute("nowShowingMovies");
                if (nowShowingMovies != null && !nowShowingMovies.isEmpty()) {
            %>
                <div class="movie-grid">
                    <%
                        for (Movie movie : nowShowingMovies) {
                    %>
                        <div class="movie-card">
                            <h3><%= movie.getTitle() %></h3>
                            <p><strong>Director:</strong> <%= movie.getDirector() %></p>
                            <p><strong>Genre:</strong> <%= movie.getGenre() %></p>
                            <p><strong>Duration:</strong> <%= movie.getDuration() %> mins</p>
                            <p><strong>Rating:</strong> ⭐ <%= movie.getRating() %></p>
                            <button class="btn btn-secondary btn-block" disabled style="opacity: 0.5; cursor: not-allowed;" title="Feature not implemented">Book Now</button>
                        </div>
                    <%
                        }
                    %>
                </div>
            <%
                } else {
            %>
                <p style="text-align: center; color: #7f8c8d;">No movies showing right now.</p>
            <%
                }
            %>

            <h2 style="margin-top: 40px;">Coming Soon</h2>
            <%
                List<Movie> comingSoonMovies = (List<Movie>) request.getAttribute("comingSoonMovies");
                if (comingSoonMovies != null && !comingSoonMovies.isEmpty()) {
            %>
                <div class="movie-grid">
                    <%
                        for (Movie movie : comingSoonMovies) {
                    %>
                        <div class="movie-card">
                            <h3><%= movie.getTitle() %></h3>
                            <p><strong>Director:</strong> <%= movie.getDirector() %></p>
                            <p><strong>Genre:</strong> <%= movie.getGenre() %></p>
                            <p><strong>Duration:</strong> <%= movie.getDuration() %> mins</p>
                            <p><strong>Release:</strong> <%= movie.getReleaseDate() %></p>
                            <span class="btn btn-secondary btn-block" style="cursor: default;">Coming Soon</span>
                        </div>
                    <%
                        }
                    %>
                </div>
            <%
                } else {
            %>
                <p style="text-align: center; color: #7f8c8d;">No upcoming movies.</p>
            <%
                }
            %>
        </main>

        <footer>
            <p>&copy; 2024 CineMan. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
