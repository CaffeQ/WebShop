package com.example.webshop;

import com.example.webshop.bo.ItemHandler;
import com.example.webshop.bo.User;
import com.example.webshop.bo.UserHandler;
import com.example.webshop.ui.ItemInfo;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
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
        String action = request.getParameter("action");

        switch (action){
            case "cart":
                // Second step of adding cookies
                // This should be in a separate request
                Cookie[] cookies = request.getCookies();
                System.out.println("Reading Cookies...");
                if(cookies != null) {
                    System.out.println("Cookie length: " + cookies.length);
                    for(Cookie c : cookies) {
                        System.out.println(c.getValue());
                    }
                } else {
                    System.out.println("No cookies found");
                }
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                break;
            case "login":
                request.getRequestDispatcher("login.jsp").forward(request,response);
                break;
            case "addItem":
                request.getRequestDispatcher("addItem.jsp").forward(request,response);
            default:

                // First step of adding cookies
                // Setting the cookie

                request.getSession().setMaxInactiveInterval(1000);
                request.getSession().setAttribute("shoppingCart", "Timmmie");


                // Place itemInfo inside request attribute & forward to JSP.
                Collection<ItemInfo> itemInfo = ItemHandler.getItems();
                request.setAttribute("itemInfo",itemInfo);
                request.getRequestDispatcher("product.jsp").forward(request,response);

                request.getSession().getAttribute("cartItemName");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request == null || response == null) {
            init(request, response);
        }

        String action = request.getParameter("action");
        System.out.println("Action : " + action);
        switch(action){
            case "addItemToCart" :
                String cartItemName = request.getParameter("cartItemName");
                String cartItemQuantity = request.getParameter("cartItemQuantity");
                System.out.println("cartItemName : " + cartItemName +", cartItemQuantity : " + cartItemQuantity);
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

    public void testGet(HttpServletRequest request, HttpServletResponse response){
        /*
        System.out.println("Request: " + request.toString());
        System.out.println("Session: " + request.getSession().toString() );
        System.out.println("Cookies: " + request.getCookies().toString());
        System.out.println("Response: "+ response.toString());

        ArrayList<IShoppingCart<ItemInfo,Integer>> shoppingCart = new ArrayList<>();
        ItemInfo tim = new ItemInfo("Tim","Thai", 5, "IN_STOCK");
        shoppingCart.add(new ShoppingCart(tim, 3));

        Serializable serializable = shoppingCart.toArray();

         */



    }
}
