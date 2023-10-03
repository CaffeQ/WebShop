package com.example.webshop;

import com.example.webshop.bo.ItemHandler;
import com.example.webshop.ui.ItemInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {

    public void init(){

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
