package com.example.webshop;

import com.example.webshop.bo.handler.ItemHandler;
import com.example.webshop.bo.handler.OrderHandler;
import com.example.webshop.bo.handler.UserHandler;
import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.OrderInfo;
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
                if(UserHandler.isUserAdmin(session))
                    request.getRequestDispatcher("item.jsp").forward(request,response);
                else{
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }
                break;
            case "edit":
                if(UserHandler.isUserAdmin(session)){
                    String name = request.getParameter("editName");
                    ItemInfo item = ItemHandler.getItemByName(name);
                    System.out.println("Item " + item);
                    session.setAttribute("item",item);
                    request.getRequestDispatcher("edit.jsp").forward(request,response);
                    response.sendRedirect("edit.jsp");
                }
                else{
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }
                break;
            case "product":
                Collection<ItemInfo> itemInfo = ItemHandler.getItems();//TODO: ItemHandler.getItems(session) - everything happens inside handler
                request.getSession().setAttribute("itemInfo",itemInfo);
                request.getRequestDispatcher("product.jsp").forward(request,response);
            case "order":
                if(UserHandler.isUserAdmin(session) || UserHandler.isUserW_Staff(session)){
                    ArrayList<OrderInfo> orders = OrderHandler.getAll();
                    request.getSession().setAttribute("order", orders); //TODO: ItemHandler.getItems(session) - everything happens inside handler
                    request.getRequestDispatcher("order.jsp").forward(request,response);
                }else {
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }
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
                if(UserHandler.authenticateUser(request)){
                    request.removeAttribute("password");
                    request.getRequestDispatcher("welcome.jsp").forward(request,response);
                    response.sendRedirect("welcome.jsp");
                }else{
                    request.setAttribute("errorMessage","Invalid login name or password");
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                    response.sendRedirect("login.jsp");
                }

                break;
            case "processEdit":
                if(UserHandler.isUserAdmin(request.getSession())){
                    request.getRequestDispatcher("edit.jsp").forward(request,response);
                    response.sendRedirect("edit.jsp");
                }else{
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }
                break;
            case "placeOrder" :
                OrderHandler.checkCartEmpty(session);
                //TODO: User need to authenticate and logged in.
                try {
                    if(UserHandler.isVerified(session)){// TODO: <--- Transaction happens here
                        OrderHandler.placeOrder(session);
                        request.getRequestDispatcher("welcome.jsp").forward(request,response);
                        response.sendRedirect("welcome.jsp");
                    }else{
                        request.setAttribute("errorMessage","Invalid verification");
                        request.getRequestDispatcher("error.jsp").forward(request,response);
                        response.sendRedirect("error.jsp");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                finally{
                    session.setAttribute("cart",OrderHandler.createNewCart());
                }
                break;
            case "processAdd":
                if(UserHandler.isUserAdmin(request.getSession())){
                    ItemHandler.addItem(request);
                    request.getRequestDispatcher("item.jsp").forward(request,response);
                    response.sendRedirect("item.jsp");
                }else{
                    request.setAttribute("errorMessage","Invalid verification");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }
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
