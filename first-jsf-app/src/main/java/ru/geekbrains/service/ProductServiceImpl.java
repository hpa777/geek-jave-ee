package ru.geekbrains.service;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService, ProductServiceRemote{

    @EJB
    private ProductRepository productRepository;
    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepresentation> findAll() {
        return productRepository.findAll().stream().map(ProductRepresentation::new).collect(Collectors.toList());
    }

    @Override
    public ProductRepresentation findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return new ProductRepresentation(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductRepresentation product) {
        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
