package com.example.webshop;

import com.example.webshop.bo.Cart;
import com.example.webshop.bo.CartItem;
import com.example.webshop.bo.ItemHandler;
import com.example.webshop.bo.User;
import com.example.webshop.bo.UserHandler;
import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {
    private HttpServletRequest request;
    private HttpServletResponse response;


    public void init(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        request.getSession().setMaxInactiveInterval(60*60*8);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request == null || response == null){
            init(request,response);
        }

        String action = request.getParameter("action");
        switch (action){
            case "cart":
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                break;
            case "login":
                request.getRequestDispatcher("login.jsp").forward(request,response);
                break;
            case "product":
                Collection<ItemInfo> itemInfo = ItemHandler.getItems();
                request.setAttribute("itemInfo",itemInfo);
                request.getRequestDispatcher("product.jsp").forward(request,response);
            default:
                Collection<ItemInfo> itemInfo1 = ItemHandler.getItems();
                request.setAttribute("itemInfo",itemInfo1);
                request.getRequestDispatcher("product.jsp").forward(request,response);

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request == null || response == null) {
            init(request, response);
        }

        String action = request.getParameter("action");
        switch(action){
            case "addItemToCart" :

                Cart cart = (Cart) request.getSession().getAttribute("cart");
                if(cart == null){
                    request.getSession().setAttribute("cart",new Cart());
                    cart = (Cart) request.getSession().getAttribute("cart");
                }

                cart.add(request.getParameter("cartItemName"), request.getParameter("cartItemQuantity"));
                request.getSession().setAttribute("cart",cart);

                request.getRequestDispatcher("cart.jsp").forward(request,response);
                response.sendRedirect("cart.jsp");
                break;

            case "processLogin":
                String userName = request.getParameter("name");
                String password = request.getParameter("password");
                System.out.println("User name = " + userName );
                System.out.println("User password = " + password );
                if(UserHandler.authenticateUser(userName,password)){
                    UserInfo userInfo = UserHandler.getUser(userName);
                    request.getSession().setAttribute("user",userInfo);
                    request.getRequestDispatcher("index.jsp").forward(request,response);
                    response.sendRedirect("index.jsp");
                }else{
                    request.setAttribute("errorMessage","Invalid login name or password");
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                    response.sendRedirect("login.jsp");
                }

                break;
            default:
                System.out.println("Incorrect");
        }
    }
}
