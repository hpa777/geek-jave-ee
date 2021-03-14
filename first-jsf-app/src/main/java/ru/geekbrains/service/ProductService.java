package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    List<ProductRepresentation> findAll();

    ProductRepresentation findById(Long id);

    Long countAll();

    void saveOrUpdate(ProductRepresentation product);

    void deleteById(Long id);

}
