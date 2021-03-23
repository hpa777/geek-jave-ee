package ru.geekbrains.controller;

import ru.geekbrains.service.RoleRepr;
import ru.geekbrains.service.RoleServiceImpl;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RoleController implements Serializable {

    @EJB
    private RoleServiceImpl roleService;


    private List<RoleRepr> roles;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        roles = roleService.getAllRoles();
    }


    public List<RoleRepr> getRoles() {
        return roles;
    }


}
