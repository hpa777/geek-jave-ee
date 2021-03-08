package ru.geekbrains.service;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ProductServiceRemote {

    List<ProductRepresentation> findAll();

    ProductRepresentation findById(Long id);

    Long countAll();

}
