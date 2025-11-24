package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {}

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        // Убеждаемся, что объект attached перед merge
        if (!entityManager.contains(user)) {
            User attachedUser = entityManager.merge(user);
            entityManager.persist(attachedUser);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public Optional<User> getUserById(Long userId) { // Изменено с int на Long
        User user = entityManager.find(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteUser(User user) {
        // Перед удалением нужно убедиться, что объект attached
        User attachedUser = entityManager.contains(user) ? user : entityManager.merge(user);
        entityManager.remove(attachedUser);
    }

    // ДОБАВЛЕН НОВЫЙ МЕТОД - для UserDetailsService
    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u LEFT JOIN FETCH u.roles where u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    // ДОБАВЛЕН НОВЫЙ МЕТОД - для удобства
    @Override
    public void deleteUserById(Long id) { // Изменено с int на Long
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
