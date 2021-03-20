package ru.geekbrains.service;


import java.util.List;

public interface ProductServiceRemote {

    List<ProductRepresentation> findAll();

    ProductRepresentation findById(Long id);

    Long countAll();

}
