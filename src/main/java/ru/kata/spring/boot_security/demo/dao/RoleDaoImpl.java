package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository; // ДОБАВЛЕНО
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository // ДОБАВЛЕНО
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getRoles() {
        return em.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    // ДОБАВЛЕН НОВЫЙ МЕТОД - для получения роли по ID
    @Override
    public Role getRoleById(Long id) {
        return em.find(Role.class, id);
    }
}
