package com.Shop.Shop.model;

import javax.persistence.*;
import java.util.Date;


@SuppressWarnings( "ALL" )
@Entity
@Table(name ="cartItem" )
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;


    @Temporal(TemporalType.DATE)
    private Date date;



    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingCart_id", referencedColumnName = "shoppingCart_id")
    private ShoppingCart cartItem;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;



    public ShoppingCart getCartItem() {
        return cartItem;
    }

    public void setCartItem(ShoppingCart cartItem) {
        this.cartItem = cartItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;

        CartItem cartItem = (CartItem) o;

        return getId().equals(cartItem.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
