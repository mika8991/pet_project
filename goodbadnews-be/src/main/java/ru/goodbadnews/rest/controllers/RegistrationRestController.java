package ru.goodbadnews.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.goodbadnews.rest.Services.UserService;
import ru.goodbadnews.rest.model.User;
import ru.goodbadnews.rest.security.Role;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class RegistrationRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public void registrationUser(@RequestBody User user) {
        user.setUserRoles(Collections.singleton(new Role("ADMIN")));
        userService.saveUser(user);
    }
}
