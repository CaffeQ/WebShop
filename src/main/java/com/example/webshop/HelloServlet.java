package com.example.webshop;

import java.io.*;
import java.sql.Connection;
import java.util.Collection;

import com.example.webshop.bo.ItemHandler;
import com.example.webshop.db.DBManager;
import com.example.webshop.ui.ItemInfo;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Silk road!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void destroy() {
    }
}