package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@ApplicationScoped
public class ProductRepository extends Repository {

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        this.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        this.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));
    }

}
