package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService{



    @Override
    public List<Role> getRoles() {
        return List.of();
    }

    @Override
    public Role getRoleByName(String name) {
        return null;
    }

    @Override
    public void addRole(Role role) {

    }
}
