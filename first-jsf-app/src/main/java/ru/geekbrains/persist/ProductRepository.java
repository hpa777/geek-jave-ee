package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll() {
        return entityManager.createNamedQuery("findAllProducts", Product.class).getResultList();
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        }
        entityManager.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteByIdProduct").setParameter("id", id).executeUpdate();
    }

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init() throws SystemException {
        if (this.countAll() == 0) {
            try {
                userTransaction.begin();
                this.saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", new BigDecimal(100)));
                this.saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", new BigDecimal(200)));
                this.saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", new BigDecimal(200)));
                userTransaction.commit();
            } catch (Exception ex) {
                userTransaction.rollback();
                logger.error("", ex);
            }
        }
    }

}
