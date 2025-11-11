package com.cineman.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Model class for Customer
 */
public class Customer {
    private int id;
    private Integer userId; // Can be null for walk-in customers
    private String fullName;
    private Date birthDate;
    private String address;
    private String email;
    private String phone;
    private boolean isMember;
    private Timestamp createdAt;

    // Empty constructor
    public Customer() {
    }

    // Full constructor
    public Customer(int id, Integer userId, String fullName, Date birthDate,
                    String address, String email, String phone, boolean isMember, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.isMember = isMember;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
