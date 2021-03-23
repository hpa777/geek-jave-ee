package ru.geekbrains.controller;


import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductRepresentation;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
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

    @Inject
    private HttpSession httpSession;

    public String logout() {
        httpSession.invalidate();
        return "/product_form.xhtml?faces-redirect-true";
    }

}
