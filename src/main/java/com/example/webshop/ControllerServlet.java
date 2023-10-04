package com.example.webshop;

import com.example.webshop.bo.Cart;
import com.example.webshop.bo.handler.ItemHandler;
import com.example.webshop.bo.handler.OrderHandler;
import com.example.webshop.bo.handler.UserHandler;
import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.OrderInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {


    public void init(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setMaxInactiveInterval(60*60*8);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        init(request,response);//TODO: When user do first action it should init...


        String action = request.getParameter("action");
        switch (action){
            case "cart":
                checkCartEmpty(request);
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                break;
            case "login":
                request.getRequestDispatcher("login.jsp").forward(request,response);
                break;
            case "product":
                Collection<ItemInfo> itemInfo = ItemHandler.getItems();
                request.setAttribute("itemInfo",itemInfo);
                request.getRequestDispatcher("product.jsp").forward(request,response);
            case "order":

                ArrayList<OrderInfo> orders = OrderHandler.getAll();
                request.getSession().setAttribute("order", orders);
                request.getRequestDispatcher("order.jsp").forward(request,response);
                break;
            case "welcome":
                request.getRequestDispatcher("welcome.jsp").forward(request,response);
                break;
            default:
                Collection<ItemInfo> itemInfo1 = ItemHandler.getItems();
                request.setAttribute("itemInfo",itemInfo1);
                request.getRequestDispatcher("product.jsp").forward(request,response);

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        init(request, response);//TODO: When user do first action it should init...

        String action = request.getParameter("action");
        System.out.println("Action : " + action);
        switch(action){
            case "addItemToCart" :
                checkCartEmpty(request);
                addToCart(request);

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
                    request.getRequestDispatcher("welcome.jsp").forward(request,response);
                    response.sendRedirect("welcome.jsp");
                }else{
                    request.setAttribute("errorMessage","Invalid login name or password");
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                    response.sendRedirect("login.jsp");
                }

                break;
            case "placeOrder" :
                System.out.println("Place order");
                checkCartEmpty(request);

                request.getRequestDispatcher("cart.jsp").forward(request,response);
                response.sendRedirect("cart.jsp");


                request.getRequestDispatcher("welcome.jsp").forward(request,response);
                response.sendRedirect("welcome.jsp");
                break;
            default:
                System.out.println("Incorrect");
        }
    }

    private void checkCartEmpty(HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            return;
        }
        request.getSession().setAttribute("cart",new Cart());
    }

    private void addToCart(HttpServletRequest request){
        checkCartEmpty(request);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.add(request.getParameter("cartItemName"), request.getParameter("cartItemQuantity"));
        request.getSession().setAttribute("cart",cart);
    }
}
