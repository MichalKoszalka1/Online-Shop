package com.Shop.Shop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@SuppressWarnings( "ALL" )
@Entity
@Table(name ="shoppingCart" )
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shoppingCart_id")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;
    @Transient
    private double totalPrice;

    @Transient
    private int itemsNumber;


    @OneToMany(cascade = CascadeType.ALL )
    private Collection<CartItem> cartItem;

    private String sessionToken;


    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public void setItemsNumber(int itemsNumber) {
        this.itemsNumber = itemsNumber;
    }

    public ShoppingCart() {
        cartItem = new ArrayList<CartItem>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Double getTotalPrice() {
        Double sum = 0.0;
        for(CartItem item : this.cartItem) {
            sum = sum + item.getProduct().getCostPrice ()*item.getQuantity();
        }
        return sum;
    }

    public int getItemsNumber() {
        return this.cartItem.size();
    }

    public Collection<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(Collection<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public void setItems(int i) {
    }
}
