package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;


@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/allUsers";
    }

    @GetMapping("/{id}/edit")
    public String editUserPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "/users/editUser";
    }

    @GetMapping("/new")
    public String newUserPage(@ModelAttribute("user") User user) {
        return "/users/addNewUser";
    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable("id") int id, @ModelAttribute("user") User updatedUser) {
        User existingUser = userService.getUser(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        existingUser.setAge(updatedUser.getAge());
        userService.updateUser(existingUser);
        return "redirect:/users";
    }


    @PostMapping()
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
