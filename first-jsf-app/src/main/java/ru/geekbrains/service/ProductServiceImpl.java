package ru.geekbrains.service;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.rest.ProductServiceRest;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductServiceRest {

    @EJB
    private ProductRepository productRepository;
    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepresentation> findAll() {
        return productRepository.findAll().stream().map(this::buildProductRepr).collect(Collectors.toList());
    }

    @Override
    public List<ProductRepresentation> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id).stream().map(this::buildProductRepr).collect(Collectors.toList());
    }

    private ProductRepresentation buildProductRepr(Product product) {
        ProductRepresentation repr = new ProductRepresentation();
        repr.setId(product.getId());
        repr.setName(product.getName());
        repr.setDescription(product.getDescription());
        repr.setPrice(product.getPrice());
        Category category = product.getCategory();
        repr.setCategoryId(category != null ? category.getId() : null);
        repr.setCategoryName(category != null ? category.getName() : null);
        return repr;
    }

    @Override
    public ProductRepresentation findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return buildProductRepr(product);
        }
        return null;
    }

    @Override
    public ProductRepresentation findByName(String name) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            return buildProductRepr(product);
        }
        return null;
    }



    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public void insert(ProductRepresentation product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @Override
    public void update(ProductRepresentation product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
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
