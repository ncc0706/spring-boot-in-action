package io.arukas.controller;

import io.arukas.domain.User;
import io.arukas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users/create")
    public User create(User user) {
        return userRepository.save(user);
    }

    @GetMapping(value = "/users/{id}")
    public User get(@PathVariable("id") String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("%s of id[%s] not found.", User.class.getSimpleName(), id))
        );
    }

}
