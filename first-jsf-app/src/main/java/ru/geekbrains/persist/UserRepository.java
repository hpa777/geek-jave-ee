package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class UserRepository extends Repository {

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new User(null, "Admin", "admin@mail.su", "79104532312"));
        this.saveOrUpdate(new User(null, "User", "user@mmm.rr", "79153453423"));
    }

}
