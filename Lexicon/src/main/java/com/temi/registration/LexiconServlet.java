package com.temi.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submitForm")
public class LexiconServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user input
        String lastName = request.getParameter("LastName");
        String firstName = request.getParameter("FirstName");
        String otherName = request.getParameter("OtherName");
        String address = request.getParameter("Address");
        String email = request.getParameter("Email_address");
        String passwrd = request.getParameter("Password");
        String comment = request.getParameter("Comment");

        // Decode URL-encoded values
        lastName = java.net.URLDecoder.decode(lastName, "UTF-8");
        firstName = java.net.URLDecoder.decode(firstName, "UTF-8");
        otherName = java.net.URLDecoder.decode(otherName, "UTF-8");
        address = java.net.URLDecoder.decode(address, "UTF-8");
        email = java.net.URLDecoder.decode(email, "UTF-8");
        passwrd = java.net.URLDecoder.decode(passwrd, "UTF-8");
        comment = java.net.URLDecoder.decode(comment, "UTF-8");

        // Validate user input for potential security risks
        if (containsSecurityRisk(lastName) || containsSecurityRisk(firstName) || containsSecurityRisk(otherName)
                || containsSecurityRisk(address) || containsSecurityRisk(email) || containsSecurityRisk(passwrd)
                || containsSecurityRisk(comment)) {
            request.setAttribute("error", "Input contains potential security risks.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            // If input is safe, store it in the database
            Connection connection = null;
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tree", "root", "kineus");
                PreparedStatement statement = connection
                        .prepareStatement("insert into data (lastName, firstName, otherName, address, email, passwrd, comment) values (?, ?, ?, ?, ?, ?, ?) ");
                statement.setString(1, lastName);
                statement.setString(2, firstName);
                statement.setString(3, otherName);
                statement.setString(4, address);
                statement.setString(5, email);
                statement.setString(6, passwrd);
                statement.setString(7, comment);
                statement.executeUpdate();
                connection.close();
                response.sendRedirect("success.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Error Code: " + e.getErrorCode());
                System.out.println("Message: " + e.getMessage());
                request.setAttribute("error", "Database error: " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    public static boolean containsSecurityRisk(String input) {
        // Check if the input is a normal English text
        if (isNormalText(input)) {
            return false; // Input is safe
        }

        // Check for potential JavaScript and SQL security risks
        String javascriptPattern = "(?i)\\b(?:function|document|eval|setTimeout|setInterval|XMLHttpRequest)\\b";
        String sqlPattern = "(?i)\\b(?:SELECT|INSERT|UPDATE|DELETE|DROP|CREATE)\\b";
        String scriptTagPattern = "(?i)<script>[\\s\\S]*?</script>";

        boolean isSecurityRisk = matchesPattern(input, javascriptPattern) || matchesPattern(input, sqlPattern)
                || matchesPattern(input, scriptTagPattern);

        if (isSecurityRisk) {
            System.out.println("Security Risk Detected: " + input);
        }

        return isSecurityRisk;
    }

    private static boolean isNormalText(String input) {
        // Check if the input contains only letters, numbers, and common punctuation
        String normalTextPattern = "[a-zA-Z0-9.,!?\\s]+";
        return input.matches(normalTextPattern);
    }

    private static boolean matchesPattern(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        return m.find();
    }

}
