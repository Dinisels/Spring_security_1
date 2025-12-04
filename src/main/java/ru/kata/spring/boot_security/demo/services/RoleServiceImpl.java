package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }


    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }
}
