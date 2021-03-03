package ru.geekbrains.controller;

import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Named
@SessionScoped
public class CartController implements Serializable {

    private final Map<Product, Integer> productMap = new HashMap<>();

    public List<Map.Entry<Product, Integer>> getProductMap() {
        Set<Map.Entry<Product, Integer>> productSet = productMap.entrySet();
        return new ArrayList<Map.Entry<Product, Integer>>(productSet);
    }

    public void addToCart(Product product) {
       int c = productMap.getOrDefault(product, 0);
       productMap.put(product, c + 1);
    }

    public String totalItems() {
        AtomicInteger sum = new AtomicInteger();
        productMap.forEach((k, v) -> sum.addAndGet(v));
        return sum.toString();
    }

    public void deleteProduct(Product product) {
        int c = productMap.get(product);
        if (c > 1) {
            productMap.put(product, c - 1);
        } else {
            productMap.remove(product);
        }
    }

}
