package ru.geekbrains.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductServiceWs {

    @WebMethod
    List<ProductRepresentation> findAll();

    @WebMethod
    ProductRepresentation findById(Long id);
}
