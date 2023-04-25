package com.Shop.Shop.controller;


import com.Shop.Shop.Service.ProductService;
import com.Shop.Shop.Service.ShoppingCartService;
import com.Shop.Shop.Service.UserService;
import com.Shop.Shop.Service.repository.UserRepository;
import com.Shop.Shop.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@SuppressWarnings( "ALL" )
@Controller
public class CartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addToCart")
    public String addToCart(HttpServletRequest request, Model model, @RequestParam("id") Long id, @RequestParam("quantity") int quantity){

        String sessionToken = (String) request.getSession (true).getAttribute("sessionToken" );
        if (sessionToken == null){
            sessionToken =UUID.randomUUID().toString();
            request.getSession ().setAttribute("sessionToken", sessionToken);
            shoppingCartService.addShoppingCart(id,sessionToken,quantity);

        } else {
         shoppingCartService.addToExistingShoppingCart (id,sessionToken,quantity);
        }
        return "redirect:shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String showShoppingCartView(HttpServletRequest request, Model model) {
        String sessionToken = (String) request.getSession(true).getAttribute("sessionToken" );
        if (sessionToken == null){
            model.addAttribute ("shoppingCart",new ShoppingCart ());
        }else {
            ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
            model.addAttribute ("shoppingCart",shoppingCart);
        }
        return "shoppingCart";
    }

    @PostMapping("/updateShoppingCart")
    public String updateCartItem(@RequestParam("item_id") Long id, @RequestParam("quantity") int quantity) {

        shoppingCartService.updateShoppingCartItem(id,quantity);
        return "redirect:shoppingCart";
    }
    @GetMapping("/removeCartItem/{id}")
    public String removeItem(@PathVariable("id") Long id, HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessionToken");
        System.out.println("got here ");
        shoppingCartService.removeCartIemFromShoppingCart(id,sessionToken);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/clearShoppingCart")
    public String clearShoopiString(HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessionToken");
        request.getSession(false).removeAttribute("sessionToken");
        shoppingCartService.clearShoppingCart(sessionToken);
        return "redirect:/shoppingCart";
    }

}
