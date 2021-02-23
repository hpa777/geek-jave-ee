package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CategoryRepository extends Repository {

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new Category(null, "Category", "Description"));
        this.saveOrUpdate(new Category(null, "Category2", "Description2"));
        this.saveOrUpdate(new Category(null, "Category3", "Description3"));
    }


}
