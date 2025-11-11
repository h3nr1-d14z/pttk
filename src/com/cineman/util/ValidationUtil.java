package com.cineman.util;

import java.util.regex.Pattern;

/**
 * Utility class for input validation
 */
public class ValidationUtil {

    // Email regex pattern
    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // Phone regex pattern (Vietnamese format)
    private static final Pattern PHONE_PATTERN =
        Pattern.compile("^(0|\\+84)(\\d{9,10})$");

    /**
     * Check if string is null or empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Check if string is not null or empty
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Validate email format
     */
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validate phone number (Vietnamese format)
     */
    public static boolean isValidPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Validate username (alphanumeric, 3-20 chars)
     */
    public static boolean isValidUsername(String username) {
        if (isEmpty(username)) {
            return false;
        }
        return username.matches("^[a-zA-Z0-9]{3,20}$");
    }

    /**
     * Validate password (minimum 6 chars)
     */
    public static boolean isValidPassword(String password) {
        if (isEmpty(password)) {
            return false;
        }
        return password.length() >= 6;
    }

    /**
     * Validate positive number
     */
    public static boolean isPositiveNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        try {
            double num = Double.parseDouble(str);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validate integer
     */
    public static boolean isInteger(String str) {
        if (isEmpty(str)) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Sanitize input to prevent XSS
     */
    public static String sanitizeInput(String input) {
        if (isEmpty(input)) {
            return input;
        }
        return input.replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#x27;")
                    .replaceAll("/", "&#x2F;");
    }
}
