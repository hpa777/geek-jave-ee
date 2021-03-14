package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    public Category getReference(Long id) {
        return  entityManager.getReference(Category.class, id);
    }

    public List<Category> findAll() {
        return entityManager.createNamedQuery("findAllCategory", Category.class).getResultList();
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllCategory", Long.class).getSingleResult();
    }


    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
        }
        entityManager.merge(category);
    }


    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteByIdCategory").setParameter("id", id).executeUpdate();
    }




}
