package ru.goodbadnews.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.goodbadnews.rest.Services.UserService;
import ru.goodbadnews.rest.model.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminRestController {

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/admin")
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }
}
