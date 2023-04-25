package com.Shop.Shop.Service;

import com.Shop.Shop.Service.repository.CartItemRepository;
import com.Shop.Shop.Service.repository.ShoppingCartRepository;
import com.Shop.Shop.model.CartItem;
import com.Shop.Shop.model.Product;
import com.Shop.Shop.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings( "ALL" )
@Service
public class ShoppingCartService {



    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;


    public ShoppingCart addShoppingCart(Long id, String sessionToken, int quantity) {
        ShoppingCart shoppingCart = new ShoppingCart ();
        CartItem cartItem = new CartItem ();
        cartItem.setQuantity(quantity);
        cartItem.setDate (new Date ());
        cartItem.setProduct (productService.getProductById (id));
        shoppingCart.getCartItem ().add(cartItem);
        shoppingCart.setSessionToken (sessionToken);
        shoppingCart.setDate (new Date ());
      return   shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart addToExistingShoppingCart(Long id, String sessionToken,int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken (sessionToken);
        Product p = productService.getProductById (id);
        Boolean productDoesExistInTheCart = false;
        if (shoppingCart !=null){
          Collection<CartItem> items = (Collection<CartItem>) shoppingCart.getCartItem ();
            for (CartItem item : items){
                if (item.getProduct ().equals (p)){
                    productDoesExistInTheCart = true;
                    item.setQuantity (item.getQuantity () + quantity);
                    shoppingCart.setCartItem (items);
                    return shoppingCartRepository.saveAndFlush (shoppingCart);
                }
            }
        }
        if (!productDoesExistInTheCart && (shoppingCart != null)){
            CartItem cartItem1 = new CartItem ();
            cartItem1.setDate (new Date ());
            cartItem1.setQuantity (quantity);
            cartItem1.setProduct (p);
            shoppingCart.getCartItem ().add (cartItem1);
            return shoppingCartRepository.saveAndFlush (shoppingCart);
        }
        return this.addShoppingCart (id,sessionToken,quantity);
    }

    public ShoppingCart getShoppingCartBySessionToken(String sessionToken) {
        return  shoppingCartRepository.findBySessionToken(sessionToken);
    }

    public CartItem updateShoppingCartItem(Long id, int quantity) {
        CartItem cartItem = cartItemRepository.findById(id).get();
        cartItem.setQuantity(quantity);
        return cartItemRepository.saveAndFlush(cartItem);

    }
    public ShoppingCart removeCartIemFromShoppingCart(Long id, String sessionToken) {
        ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
        Collection<CartItem> items = shoppingCart.getCartItem ();
        CartItem cartItem = null;
        for(CartItem item : items) {
            if(Objects.equals (item.getId ( ), id)) {
                cartItem = item;
            }
        }
        items.remove(cartItem);
        cartItemRepository.delete(cartItem);
        shoppingCart.setCartItem (items);
        return shoppingCartRepository.save(shoppingCart);
    }


    public void clearShoppingCart(String sessionToken) {
        ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
        shoppingCartRepository.delete(shoppingCart);

    }
}
