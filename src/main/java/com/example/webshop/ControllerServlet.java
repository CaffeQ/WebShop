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

/**
 * Handles the main routing for the web application.
 * Forwards requests to the appropriate views (JSP files) and interacts with the BO layer.
 */
@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {

    /**
     * Handles GET requests and routes them to the appropriate action.
     *
     * @param request The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object for sending responses back to the client.
     * @throws IOException If an I/O error occurs.
     * @throws ServletException If the request cannot be handled.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch (action){
            case "user":
                if(UserHandler.isUserAdmin(session)){

                    String userFilter = request.getParameter("userFilter");

                    if(userFilter == null){
                        userFilter = "all";
                    }

                    switch (userFilter){
                        case "all" :
                            request.getSession().setAttribute("users",UserHandler.getAllUsers());
                            request.getRequestDispatcher("user.jsp").forward(request,response);
                            break;

                        case "active" :
                            request.getSession().setAttribute("users",UserHandler.getAllUsersByStatus(userFilter));
                            request.getRequestDispatcher("user.jsp").forward(request,response);
                            break;

                        case "inactive" :
                            request.getSession().setAttribute("users",UserHandler.getAllUsersByStatus(userFilter));
                            request.getRequestDispatcher("user.jsp").forward(request,response);
                            break;
                    }
                }
                else{
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                }
                break;
            case "sign_in":
                request.getRequestDispatcher("sign_in.jsp").forward(request,response);
                break;
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
                }
                else{
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                }
                break;
            case "edit":
                if(UserHandler.isUserAdmin(session)){
                    String name = request.getParameter("editName");
                    session.setAttribute("item",ItemHandler.getItemByName(name));
                    request.setAttribute("errorMessage","");
                    request.getRequestDispatcher("edit.jsp").forward(request,response);
                }
                else{
                    request.setAttribute("errorMessage","Invalid privilege");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
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
                }
                break;

            case "welcome":
                request.getRequestDispatcher("welcome.jsp").forward(request,response);
                response.sendRedirect("welcome.jsp");
                break;

            default:
                request.setAttribute("errorMessage", "Get action does not exits");
                request.getRequestDispatcher("error.jsp").forward(request,response);
                break;

        }
    }

    /**
     * Handles POST requests and routes them to the appropriate action.
     *
     * @param request The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object for sending responses back to the client.
     * @throws IOException If an I/O error occurs.
     * @throws ServletException If the request cannot be handled.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch(action){
            case "processSign_in":
                if(UserHandler.createUser(request)){
                    response.sendRedirect("welcome.jsp");
                } else{
                    request.setAttribute("errorMessage","User mail already exists!");
                    request.getRequestDispatcher("error.jsp").forward(request,response);
                }
                break;
            case "addItemToCart" :
                CartHandler.addToCart(request);
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                break;

            case "processLogin":
                if(UserHandler.authenticateUser(request)){
                    request.removeAttribute("password");
                    response.sendRedirect("welcome.jsp");
                }else{
                    request.setAttribute("errorMessage","Invalid login name or password");
                    response.sendRedirect("login.jsp");
                }
                break;

            case "removeItemFromCart":
                CartHandler.removeItem(request);
                response.sendRedirect("controller-servlet?action=cart");
                break;

            case "changeUserRole":
                if(UserHandler.isUserAdmin(session)){
                    UserHandler.changeUserRole(request);
                    response.sendRedirect("controller-servlet?action=user");
                }
                else{
                    request.setAttribute("errorMessage","Cannot change role");
                    response.sendRedirect("error.jsp");
                }
                break;


            case "removeUser":
                if(UserHandler.isUserAdmin(session)){

                    UserHandler.removeUserByUserID(request);
                    response.sendRedirect("controller-servlet?action=user");
                }
                else{
                    request.setAttribute("errorMessage","Cannot remove user");
                    response.sendRedirect("error.jsp");
                }
                break;

            case "activateUser":
                if(UserHandler.isUserAdmin(session)){

                    UserHandler.activateUserByUserID(request);
                    response.sendRedirect("controller-servlet?action=user");
                }
                else{
                    request.setAttribute("errorMessage","Cannot remove user");
                    response.sendRedirect("error.jsp");
                }
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
                    response.sendRedirect("error.jsp");
                    break;
                }

                if(ItemHandler.editItem(request)){
                    response.sendRedirect("controller-servlet?action=product");
                }else {
                    request.setAttribute("errorMessage","Invalid item parameters");
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
                        response.sendRedirect("welcome.jsp");
                    }else{
                        request.setAttribute("errorMessage","Invalid verification");
                        response.sendRedirect("error.jsp");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

            case "createItem":
                if(!UserHandler.isUserAdmin(request.getSession())){
                    request.setAttribute("errorMessage","Invalid verification");
                    response.sendRedirect("error.jsp");
                }

                try {
                    if(ItemHandler.addItem(request)){
                        response.sendRedirect("controller-servlet?action=product");
                    }
                    else{
                        request.setAttribute("errorMessage","Invalid item parameters");
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
                    response.sendRedirect("error.jsp");
                }
                break;

            default:
                request.setAttribute("errorMessage", "Post action does not exits");
                response.sendRedirect("error.jsp");
                break;
        }
    }

}
