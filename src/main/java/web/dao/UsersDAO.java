package web.dao;


import org.springframework.stereotype.Service;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsersDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }
    @Transactional
    public void deleteUser(User user) {
        entityManager.remove(user);
    }
    @Transactional
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
