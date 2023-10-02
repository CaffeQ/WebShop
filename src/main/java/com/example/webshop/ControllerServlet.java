package com.example.webshop;

import com.example.webshop.ui.ItemInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {

    public void init(){

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        switch (action){
            case "cart":
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                break;
            case "login":
                request.getRequestDispatcher("login.jsp").forward(request,response);
                break;
            default:
                request.getRequestDispatcher("product.jsp").forward(request,response);
        }

    }
    public void testGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Request: " + request.toString());
        System.out.println("Session: " + request.getSession().toString() );
        System.out.println("Cookies: " + request.getCookies().toString());
        System.out.println("Response: "+ response.toString());

        request.getSession().setAttribute("Item",new ItemInfo("Tim","Thai", 5, "IN_STOCK"));
        ItemInfo itemInfo = (ItemInfo) request.getSession().getAttribute("Item");
        System.out.println("Item: "+itemInfo.toString());
    }
}
