package io.arukas.controller;

import io.arukas.model.User;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.randomizers.text.StringRandomizer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

import static io.github.benas.randombeans.FieldDefinitionBuilder.field;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("")
    public List<User> users(){
        return EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(field().named("nickname").ofType(String.class).inClass(User.class).get(),
                        new StringRandomizer(5, 30, 10))
                .build()
                .objects(User.class, 20)
                .collect(Collectors.toList());
    }

}
