package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RoleService {

    List<RoleRepr> getAllRoles();

    RoleRepr findById(Long id);

    Long countAll();

}
