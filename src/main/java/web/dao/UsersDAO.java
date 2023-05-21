package web.dao;


import org.springframework.stereotype.Component;
import web.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDAO {
    private final List<User> usersList;

    public UsersDAO() {
        this.usersList = new ArrayList<>();
        usersList.add(new User("Ivan", "Sizov", 30));
        usersList.add(new User("Elena", "Smith", 35));
        usersList.add(new User("John", "Doe", 83));
    }

    public User getUser(int id) {
        return usersList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addUser(User user) {
        usersList.add(new User(user.getName(), user.getSurname(), user.getAge()));
    }

    public void updateUser(int id, String name, String surname, int age) {
        usersList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .ifPresent(user -> {
                    user.setName(name);
                    user.setSurname(surname);
                    user.setAge(age);
                });
    }

    public void deleteUser(int id) {
        usersList.removeIf(user -> user.getId() == id);
    }

    public List<User> getAllUsers() {
        return usersList;
    }
}
