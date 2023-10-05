package com.example.webshop;

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
                    session.setAttribute("item",ItemHandler.getItemByName(name));
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
                    request.getSession().setAttribute("order", OrderHandler.getAll());
                    request.getRequestDispatcher("order.jsp").forward(request,response);
                }else {
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
                request.getRequestDispatcher("error.jsp").forward(request,response);
                response.sendRedirect("error.jsp");
                break;

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        System.out.println("action: " + action);
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
                    System.out.println("Inside");
                    ItemHandler.editItem(request);
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
                try {
                    if(UserHandler.isVerified(session)){
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
                request.getRequestDispatcher("error.jsp").forward(request,response);
                response.sendRedirect("error.jsp");
                break;
        }
    }

}
