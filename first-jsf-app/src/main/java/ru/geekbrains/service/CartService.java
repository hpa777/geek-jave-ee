package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface CartService {
    void addToCart(ProductRepresentation product);

    List<Map.Entry<ProductRepresentation, Integer>> getProductMap();

    String totalItems();

    void deleteProduct(ProductRepresentation product);
}
