package com.cineman.model;

import java.sql.Timestamp;

/**
 * Model class for Invoice
 */
public class Invoice {
    private int id;
    private String invoiceNumber;
    private Integer customerId;
    private Integer staffId;
    private int showtimeId;
    private Timestamp createdAt;
    private double totalAmount;
    private double discount;
    private double finalAmount;
    private String paymentMethod; // CASH, CARD, ONLINE, WALLET
    private String status; // PENDING, PAID, CANCELLED, REFUNDED
    private String notes;

    // Empty constructor
    public Invoice() {
    }

    // Full constructor
    public Invoice(int id, String invoiceNumber, Integer customerId, Integer staffId,
                   int showtimeId, Timestamp createdAt, double totalAmount, double discount,
                   double finalAmount, String paymentMethod, String status, String notes) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.customerId = customerId;
        this.staffId = staffId;
        this.showtimeId = showtimeId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.notes = notes;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
