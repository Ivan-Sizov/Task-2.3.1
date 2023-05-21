package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UsersDAO;
import web.models.User;

import javax.transaction.Transactional;


@Controller
@RequestMapping("users")
@Transactional
public class UsersController {
    private final UsersDAO usersDAO;

    @Autowired
    public UsersController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", usersDAO.getAllUsers());
        return "users/allUsers";
    }

    @GetMapping("/{id}/edit")
    public String editUserPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", usersDAO.getUser(id));
        return "/users/editUser";
    }

    @GetMapping("/new")
    public String newUserPage(@ModelAttribute("user") User user) {
        return "/users/addNewUser";
    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable("id") int id, @ModelAttribute("user") User updatedUser) {
        User existingUser = usersDAO.getUser(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        existingUser.setAge(updatedUser.getAge());
        usersDAO.updateUser(existingUser);
        return "redirect:/users";
    }


    @PostMapping()
    public String addNewUser(@ModelAttribute("user") User user) {
        usersDAO.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        usersDAO.deleteUser(usersDAO.getUser(id));
        return "redirect:/users";
    }
}
