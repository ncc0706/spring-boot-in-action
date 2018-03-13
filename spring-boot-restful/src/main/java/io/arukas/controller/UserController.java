package io.arukas.controller;

import io.arukas.entity.User;
import io.arukas.service.UserService;
import io.arukas.tools.Dump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    public Dump user(@RequestBody User user){
        user = userService.create(user);
        return Dump.success(user);
    }

}
