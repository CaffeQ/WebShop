package com.example.webshop;

import com.example.webshop.bo.CartHandler;
import com.example.webshop.bo.ItemHandler;
import com.example.webshop.bo.OrderHandler;
import com.example.webshop.bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch (action){
            case "cart":
                CartHandler.checkCartEmpty(session);
                CartHandler.getCartList(request);
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                break;

            case "login":
                request.getRequestDispatcher("login.jsp").forward(request,response);
                break;

            case "item":
                if(UserHandler.isUserAdmin(session)){
                    request.getRequestDispatcher("item.jsp").forward(request,response);
                    response.sendRedirect("item.jsp");
                }
                else{
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }
                break;
            case "edit":
                if(UserHandler.isUserAdmin(session)){
                    String name = request.getParameter("editName");
                    session.setAttribute("item",ItemHandler.getItemByName(name));
                    request.setAttribute("errorMessage","");
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
                request.getSession().setAttribute("itemInfo",ItemHandler.getItems());
                request.getRequestDispatcher("product.jsp").forward(request,response);
                break;

            case "order":
                if(UserHandler.isUserAdmin(session) || UserHandler.isUserW_Staff(session)){

                    String orderFilter = request.getParameter("orderFilter");

                    if(orderFilter == null){
                        request.getSession().setAttribute("orderFilter","all");
                        orderFilter = "all";
                    }

                    System.out.println(orderFilter);

                    if(orderFilter.equals("all")){
                        request.getSession().setAttribute("order", OrderHandler.getAll());
                        request.getRequestDispatcher("order.jsp").forward(request,response);
                    }
                    else{
                        request.getSession().setAttribute("order", OrderHandler.getOrderByStatus(orderFilter));
                        request.getRequestDispatcher("order.jsp").forward(request,response);
                    }
                    break;

                }
                else {
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }
                break;

            case "welcome":
                request.getRequestDispatcher("welcome.jsp").forward(request,response);
                response.sendRedirect("welcome.jsp");
                break;

            default:
                request.setAttribute("errorMessage", "Get action does not exits");
                request.getRequestDispatcher("error.jsp").forward(request,response);
                response.sendRedirect("error.jsp");
                break;

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch(action){
            case "addItemToCart" :
                CartHandler.addToCart(request);
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

            case "removeItemFromCart":
                CartHandler.removeItem(request);
                response.sendRedirect("controller-servlet?action=cart");
                break;


            case "removeItem":
                if(UserHandler.isUserAdmin(session)){
                    ItemHandler.removeItem(request);
                    response.sendRedirect("controller-servlet?action=product");
                }
                else{
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }
                break;

            case "editItem":
                if(!UserHandler.isUserAdmin(request.getSession())){
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                    break;
                }

                if(ItemHandler.editItem(request)){
                    response.sendRedirect("controller-servlet?action=product");
                }else {
                    request.setAttribute("errorMessage","Invalid item parameters");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }
                break;

            case "placeOrder" :
                CartHandler.checkCartEmpty(session);
                try {
                    if(UserHandler.isVerified(session)){
                        OrderHandler.placeOrder(session);
                        session.setAttribute("cart", CartHandler.createNewCart());
                        session.setAttribute("cartList", CartHandler.createNewCartList());
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

                break;

            case "createItem":
                if(!UserHandler.isUserAdmin(request.getSession())){
                    request.setAttribute("errorMessage","Invalid verification");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                    response.sendRedirect("error.jsp");
                }

                try {
                    if(ItemHandler.addItem(request)){
                        response.sendRedirect("controller-servlet?action=product");
                    }
                    else{
                        request.setAttribute("errorMessage","Invalid item parameters");
                        request.getRequestDispatcher("error.jsp").forward(request,response);
                        response.sendRedirect("error.jsp");
                    }
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

            case "sendOrder":
                if(UserHandler.isUserAdmin(session) || UserHandler.isUserW_Staff(session)) {
                    try {
                        OrderHandler.sendOrder(request);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    response.sendRedirect("controller-servlet?action=order");
                }
                else {
                    request.setAttribute("errorMessage", "Invalid verification");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    response.sendRedirect("error.jsp");
                }
                break;

            default:
                request.setAttribute("errorMessage", "Post action does not exits");
                request.getRequestDispatcher("error.jsp").forward(request,response);
                response.sendRedirect("error.jsp");
                break;
        }
    }

}
