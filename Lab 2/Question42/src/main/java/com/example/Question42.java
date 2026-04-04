package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "Question42", urlPatterns = {"/display"})
public class Question42 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Raka Maharjan</title></head><body style='font-family:Arial'>");

        // Print the sentence 10 times
        for (int i = 1; i <= 10; i++) {
            out.println("<h3>" + i + ". Java is one of the powerful programming languages!</h3>");
        }

        // Current date and time
        Date now = new Date();
        out.println("<h2>Current Date and Time: " + now + "</h2>");

        // Replace these with YOUR details
        out.println("<h2>Your Name: Raka Maharjan</h2>");
        out.println("<h2>Roll No: 2308-1002</h2>");
        out.println("<h2>Section: CSIT 7 Semester</h2>");

        out.println("</body></html>");
    }
}