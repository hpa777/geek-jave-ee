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
public class UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        return entityManager.createNamedQuery("findAllUsers", User.class).getResultList();
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllUsers", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        }
        entityManager.merge(user);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteByIdUser").setParameter("id", id).executeUpdate();
    }

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init() throws SystemException {
        if (this.countAll() == 0) {
            try {
                userTransaction.begin();
                this.saveOrUpdate(new User(null, "Admin", "admin@mail.su", "79104532312", "qwe123"));
                this.saveOrUpdate(new User(null, "User", "user@mmm.rr", "79153453423", "zxc123"));
                userTransaction.commit();
            } catch (Exception ex) {
                userTransaction.rollback();
                logger.error("", ex);
            }

        }

    }

}
