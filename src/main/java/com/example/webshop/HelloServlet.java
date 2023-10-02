package com.example.webshop;

import java.io.*;
import java.sql.Connection;

import com.example.webshop.bo.ItemHandler;
import com.example.webshop.db.DBManager;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Silk road!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Connection con = DBManager.getConnection();
        if(con==null)
            System.out.println("Failed to connect!");
        else
            System.out.println("Connected");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}