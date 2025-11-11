package com.cineman.model;

import java.sql.Date;

/**
 * Model class for Membership card
 */
public class Membership {
    private int id;
    private int customerId;
    private String cardNumber;
    private Date registrationDate;
    private Date expiryDate;
    private int points;
    private String cardType; // SILVER, GOLD, PLATINUM
    private String status; // ACTIVE, EXPIRED, SUSPENDED

    // Empty constructor
    public Membership() {
    }

    // Full constructor
    public Membership(int id, int customerId, String cardNumber, Date registrationDate,
                      Date expiryDate, int points, String cardType, String status) {
        this.id = id;
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.registrationDate = registrationDate;
        this.expiryDate = expiryDate;
        this.points = points;
        this.cardType = cardType;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
