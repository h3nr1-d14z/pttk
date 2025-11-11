package com.cineman.servlet;

import com.cineman.dao.CustomerDAO;
import com.cineman.dao.MembershipDAO;
import com.cineman.dao.UserDAO;
import com.cineman.model.Customer;
import com.cineman.model.Membership;
import com.cineman.model.User;
import com.cineman.util.DateUtil;
import com.cineman.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Servlet for customer membership registration
 * Flow: Input info → Save to DB (User + Customer + Membership) → Show success with card number
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;
    private CustomerDAO customerDAO;
    private MembershipDAO membershipDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        customerDAO = new CustomerDAO();
        membershipDAO = new MembershipDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to register page
        request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set character encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Get parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthDateStr = request.getParameter("birthDate");
        String address = request.getParameter("address");

        // Validate input
        StringBuilder errors = new StringBuilder();

        if (ValidationUtil.isEmpty(username)) {
            errors.append("Username is required. ");
        } else if (!ValidationUtil.isValidUsername(username)) {
            errors.append("Username must be 3-20 alphanumeric characters. ");
        } else if (userDAO.usernameExists(username)) {
            errors.append("Username already exists. ");
        }

        if (ValidationUtil.isEmpty(password)) {
            errors.append("Password is required. ");
        } else if (!ValidationUtil.isValidPassword(password)) {
            errors.append("Password must be at least 6 characters. ");
        }

        if (!password.equals(confirmPassword)) {
            errors.append("Passwords do not match. ");
        }

        if (ValidationUtil.isEmpty(fullName)) {
            errors.append("Full name is required. ");
        }

        if (ValidationUtil.isEmpty(phone)) {
            errors.append("Phone is required. ");
        } else if (!ValidationUtil.isValidPhone(phone)) {
            errors.append("Invalid phone number format. ");
        }

        if (ValidationUtil.isNotEmpty(email) && !ValidationUtil.isValidEmail(email)) {
            errors.append("Invalid email format. ");
        }

        // If there are validation errors, return to form
        if (errors.length() > 0) {
            request.setAttribute("error", errors.toString());
            request.setAttribute("username", username);
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("birthDate", birthDateStr);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
            return;
        }

        try {
            // Create User
            User user = new User();
            user.setUsername(username);
            user.setPassword(password); // In production, hash this password
            user.setRole("CUSTOMER");
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setActive(true);

            // Insert user
            if (userDAO.insertUser(user)) {
                // Get the inserted user to get the ID
                User insertedUser = userDAO.getUserByUsername(username);

                // Create Customer
                Customer customer = new Customer();
                customer.setUserId(insertedUser.getId());
                customer.setFullName(fullName);

                if (ValidationUtil.isNotEmpty(birthDateStr)) {
                    Date birthDate = DateUtil.stringToDate(birthDateStr);
                    customer.setBirthDate(birthDate);
                }

                customer.setAddress(address);
                customer.setEmail(email);
                customer.setPhone(phone);
                customer.setMember(false); // Initially not a member

                // Insert customer
                int customerId = customerDAO.insertCustomer(customer);

                if (customerId > 0) {
                    // Create Membership card
                    String cardNumber = membershipDAO.generateCardNumber();

                    Membership membership = new Membership();
                    membership.setCustomerId(customerId);
                    membership.setCardNumber(cardNumber);
                    membership.setRegistrationDate(new Date(System.currentTimeMillis()));

                    // Set expiry date to 1 year from now
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    cal.add(java.util.Calendar.YEAR, 1);
                    membership.setExpiryDate(new Date(cal.getTimeInMillis()));

                    membership.setPoints(0);
                    membership.setCardType("SILVER");
                    membership.setStatus("ACTIVE");

                    // Insert membership
                    if (membershipDAO.insertMembership(membership)) {
                        // Update customer to be a member
                        customer.setId(customerId);
                        customer.setMember(true);
                        customerDAO.updateCustomer(customer);

                        // Show success with card number
                        request.setAttribute("success", "Registration successful! Your membership card has been created.");
                        request.setAttribute("cardNumber", cardNumber);
                        request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
                    } else {
                        // Membership creation failed, but customer exists
                        request.setAttribute("success", "Registration successful! Please login.");
                        request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
                    }
                } else {
                    // Failed to create customer
                    request.setAttribute("error", "Registration failed. Please try again.");
                    request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
                }
            } else {
                // Failed to create user
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during registration: " + e.getMessage());
            request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
        }
    }
}
