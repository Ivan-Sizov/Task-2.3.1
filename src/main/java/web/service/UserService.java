package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDAOImpl;
import web.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserDAOImpl usersDAO;

    @Autowired
    public UserService(UserDAOImpl usersDAO) {
        this.usersDAO = usersDAO;
    }

    public List<User> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    public User getUser(int id) {
        return usersDAO.getUser(id);
    }

    public void addUser(User user) {
        usersDAO.addUser(user);
    }

    public void updateUser(User user) {
        usersDAO.updateUser(user);
    }

    public void deleteUser(int id) {
        User user = usersDAO.getUser(id);
        usersDAO.deleteUser(user);
    }
}
