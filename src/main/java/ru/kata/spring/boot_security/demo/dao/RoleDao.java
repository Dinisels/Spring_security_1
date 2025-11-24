package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRoles();
    Role getRoleByName(String name);
    void addRole(Role role);
    public Role getRoleById(Long id);
}
