package ru.geekbrains.controller;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.service.ProductRepresentation;
import ru.geekbrains.service.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryRepository categoryRepository;

    private ProductRepresentation product;

    private List<ProductRepresentation> products;

    private List<Category> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        products = productService.findAll();
        categories = categoryRepository.findAll();
    }

    public ProductRepresentation getProduct() {
        return product;
    }

    public void setProduct(ProductRepresentation product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new ProductRepresentation();
        return "/product_form.xhtml?faces-redirect-true";
    }

    public List<ProductRepresentation> getAllProducts() {
        return products;
    }

    public String editProduct(ProductRepresentation product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect-true";
    }

    public void deleteProduct(ProductRepresentation product) {
        productService.deleteById(product.getId());
    }

    public String saveProduct() {
        productService.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect-true";
    }

    public List<Category> getCategories() {
        return categories;
    }
}
