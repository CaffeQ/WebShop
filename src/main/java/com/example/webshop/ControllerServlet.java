package com.example.webshop;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch (action){
            case "cart":
                OrderHandler.checkCartEmpty(session);
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                break;
            case "login":
                request.getRequestDispatcher("login.jsp").forward(request,response);
                break;
            case "item":
                request.getRequestDispatcher("item.jsp").forward(request,response);
            case "product":
                Collection<ItemInfo> itemInfo = ItemHandler.getItems();//TODO: ItemHandler.getItems(session) - everything happens inside handler
                request.getSession().setAttribute("itemInfo",itemInfo);
                request.getRequestDispatcher("product.jsp").forward(request,response);
            case "order":
                ArrayList<OrderInfo> orders = OrderHandler.getAll();
                request.getSession().setAttribute("order", orders); //TODO: ItemHandler.getItems(session) - everything happens inside handler
                request.getRequestDispatcher("order.jsp").forward(request,response);
                break;
            case "welcome":
                request.getRequestDispatcher("welcome.jsp").forward(request,response);
                break;
            default:
                Collection<ItemInfo> itemInfo1 = ItemHandler.getItems();
                request.getSession().setAttribute("itemInfo",itemInfo1); //TODO: ItemHandler.getItems(session) - everything happens inside handler
                request.getRequestDispatcher("product.jsp").forward(request,response);

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        switch(action){
            case "addItemToCart" :
                OrderHandler.addToCart(request);
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
                OrderHandler.checkCartEmpty(session);
                //TODO: User need to authenticate and logged in.
                try {
                    OrderHandler.placeOrder(session); // TODO: <--- Transaction happens here
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                finally{
                    session.setAttribute("cart",OrderHandler.createNewCart());
                }

                request.getRequestDispatcher("welcome.jsp").forward(request,response);
                response.sendRedirect("welcome.jsp");

                break;
            case "processAdd":
                ItemHandler.adminAddItem(request);
                request.getRequestDispatcher("item.jsp").forward(request,response);
                break;

            case "sendOrder":
                System.out.println("sendOrder");
                try {
                    OrderHandler.sendOrder(request);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect("controller-servlet?action=order");
                break;

            default:
                System.out.println("Incorrect");
        }
    }

}
