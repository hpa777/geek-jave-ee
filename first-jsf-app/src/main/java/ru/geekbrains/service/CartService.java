package ru.geekbrains.service;

import ru.geekbrains.persist.Product;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Local
public interface CartService {
    void addToCart(ProductRepresentation product);

    List<Map.Entry<ProductRepresentation, Integer>> getProductMap();

    String totalItems();

    void deleteProduct(ProductRepresentation product);
}
