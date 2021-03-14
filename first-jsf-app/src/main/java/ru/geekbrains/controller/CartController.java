package ru.geekbrains.controller;


import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductRepresentation;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

import java.util.*;



@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public List<Map.Entry<ProductRepresentation, Integer>> getProductMap() {
        return cartService.getProductMap();
    }

    public void addToCart(ProductRepresentation product) {
       cartService.addToCart(product);
    }

    public String totalItems() {
        return cartService.totalItems();
    }

    public void deleteProduct(ProductRepresentation product) {
        cartService.deleteProduct(product);
    }

}
