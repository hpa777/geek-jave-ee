package ru.geekbrains.controller;

import ru.geekbrains.persist.*;
import ru.geekbrains.service.RoleRepr;
import ru.geekbrains.service.UserRepr;
import ru.geekbrains.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UserService userService;



    private UserRepr user;

    private List<UserRepr> users;

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public UserRepr getUser() {
        return user;
    }

    public void setUser(UserRepr user) {
        this.user = user;
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        users = userService.getAllUsers();

    }

    public String createUser() {
        this.user = new UserRepr();
        return "user_form.xhtml?faces-redirect-true";
    }

    public List<UserRepr> getAllUsers() {
        return users;
    }

    public String editUser(UserRepr user) {
        this.user = user;
        return "user_form.xhtml?faces-redirect-true";
    }

    public void deleteUser(UserRepr user) {
        userService.delete(user.getId());
    }

    public String saveUser() {
        userService.saveOrUpdate(user);
        return "user.xhtml?faces-redirect-true";
    }
}
