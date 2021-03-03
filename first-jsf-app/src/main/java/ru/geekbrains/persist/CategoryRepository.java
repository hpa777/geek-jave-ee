package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    public List<Category> findAll() {
        return entityManager.createNamedQuery("findAllCategory", Category.class).getResultList();
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllCategory", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
        }
        entityManager.merge(category);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteByIdCategory").setParameter("id", id).executeUpdate();
    }

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init() throws SystemException {
        if (this.countAll() == 0) {
            try {
                userTransaction.begin();
                this.saveOrUpdate(new Category(null, "Category1", "Description1"));
                this.saveOrUpdate(new Category(null, "Category2", "Description2"));
                this.saveOrUpdate(new Category(null, "Category3", "Description3"));
                userTransaction.commit();
            } catch (Exception ex) {
                userTransaction.rollback();
                logger.error("", ex);
            }
        }
    }


}
