package com.example.webshop;

import com.example.webshop.bo.Cart;
import com.example.webshop.bo.CartItem;
import com.example.webshop.bo.ItemHandler;
import com.example.webshop.ui.ItemInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
                request.getSession().setAttribute("cart", Cart.getCart());

                /*
                ArrayList<CartItem<ItemInfo>> carts = (ArrayList<CartItem<ItemInfo>>) request.getAttribute("cart");

                for(int i=0;i<carts.size();i++){
                    System.out.println(carts.get(i).getItem().toString() + ", quantity : " + carts.get(i).getQuantity() );
                }*/


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

                // Place itemInfo inside request attribute & forward to JSP.
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
        System.out.println("Action : " + action);
        switch(action){
            case "addItemToCart" :

                String cartItemName = request.getParameter("cartItemName");
                String cartItemQuantity = request.getParameter("cartItemQuantity");

                ArrayList<CartItem<ItemInfo>> cart = (ArrayList<CartItem<ItemInfo>>) request.getSession().getAttribute("cart");
                if(cart == null){
                    Cart.init();
                    cart = Cart.getCart();
                    request.getSession().setAttribute("cart",cart);
                    cart.add(Cart.add(cartItemName,cartItemQuantity));
                }
                else{
                    cart = (ArrayList<CartItem<ItemInfo>>) request.getSession().getAttribute("cart");
                    cart.add(Cart.add(cartItemName,cartItemQuantity));
                }

                for(int i=0;i<cart.size();i++){
                    System.out.println(cart.get(i).getItem().toString() + ", quantity : " + cart.get(i).getQuantity() );
                }

                request.getSession().setAttribute("cart",cart);


                //System.out.println("cartItemName : " + cartItemName +", cartItemQuantity : " + cartItemQuantity);
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                response.sendRedirect("cart.jsp");
            default:
                System.out.println("Incorrect");
        }
    }
}
