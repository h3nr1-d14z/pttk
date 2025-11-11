package com.cineman.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Model class for Movie
 */
public class Movie {
    private int id;
    private String title;
    private String englishTitle;
    private String director;
    private String actors;
    private String genre;
    private String country;
    private int duration; // in minutes
    private String description;
    private String posterUrl;
    private String trailerUrl;
    private Date releaseDate;
    private Date endDate;
    private double rating;
    private String ageRating; // G, PG, PG13, R, NC17
    private String status; // COMING_SOON, NOW_SHOWING, ENDED
    private Timestamp createdAt;

    // Empty constructor
    public Movie() {
    }

    // Full constructor
    public Movie(int id, String title, String englishTitle, String director, String actors,
                 String genre, String country, int duration, String description, String posterUrl,
                 String trailerUrl, Date releaseDate, Date endDate, double rating,
                 String ageRating, String status, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.englishTitle = englishTitle;
        this.director = director;
        this.actors = actors;
        this.genre = genre;
        this.country = country;
        this.duration = duration;
        this.description = description;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.rating = rating;
        this.ageRating = ageRating;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
