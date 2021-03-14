package ru.geekbrains.service;

import javax.ejb.Stateful;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Stateful
public class CartServiceImpl implements CartService{

    private final Map<ProductRepresentation, Integer> productMap = new HashMap<>();

    @Override
    public void addToCart(ProductRepresentation product) {
        int c = productMap.getOrDefault(product, 0);
        productMap.put(product, c + 1);
    }

    @Override
    public List<Map.Entry<ProductRepresentation, Integer>> getProductMap() {
        Set<Map.Entry<ProductRepresentation, Integer>> productSet = productMap.entrySet();
        return new ArrayList<Map.Entry<ProductRepresentation, Integer>>(productSet);
    }

    @Override
    public String totalItems() {
        AtomicInteger sum = new AtomicInteger();
        productMap.forEach((k, v) -> sum.addAndGet(v));
        return sum.toString();
    }

    @Override
    public void deleteProduct(ProductRepresentation product) {
        int c = productMap.get(product);
        if (c > 1) {
            productMap.put(product, c - 1);
        } else {
            productMap.remove(product);
        }
    }
}
