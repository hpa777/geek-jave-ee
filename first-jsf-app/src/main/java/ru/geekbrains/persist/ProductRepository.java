package ru.geekbrains.persist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public Product findByName(String name) {
        return entityManager.createNamedQuery("findByNameProduct", Product.class).setParameter("name", name).getSingleResult();
    }

    public List<Product> findAll() {
        return entityManager.createNamedQuery("findAllProducts", Product.class).getResultList();
    }

    public List<Product> findByCategoryId(Long id) {
        return entityManager.createNamedQuery("findByCategoryIdProducts", Product.class).setParameter("id", id).getResultList();
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }


    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        }
        entityManager.merge(product);
    }


    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteByIdProduct").setParameter("id", id).executeUpdate();
    }


}
