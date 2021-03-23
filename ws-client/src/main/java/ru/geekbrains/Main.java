package ru.geekbrains;

import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.ProductServiceWs;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:8080/first-jsf-app/ProductService/ProductServiceImpl?wsdl");
        ProductService productService = new ProductService();
        ProductServiceWs port = productService.getProductServiceImplPort();

        port.findAll().forEach(product -> System.out.println(product.getName()));

    }
}
