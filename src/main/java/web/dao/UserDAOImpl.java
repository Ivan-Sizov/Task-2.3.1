package web.dao;

import web.models.User;

import java.util.List;

public interface UserDAOImpl {
    User getUser(int id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    List<User> getAllUsers();
}
