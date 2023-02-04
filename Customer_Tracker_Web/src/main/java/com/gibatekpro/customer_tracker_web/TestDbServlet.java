package com.gibatekpro.customer_tracker_web;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class TestDbServlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //set up connection variables
        String user = "springstudent";
        String pass = "springstudent";

        String jdbcurl = "jdbc:mysql://localhost:3306/web_customer_tracker?allowPublicKeyRetrieval=true&useSSL=false&serverTimeZone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        try {
            //get connection to database
            Connection connection = DriverManager.getConnection(jdbcurl, user, pass);
            PrintWriter out = response.getWriter();
            out.println("Connection Successful!!!");

            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");

            System.out.println("Connection Successful!!!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void destroy() {
    }
}