package io.arukas.controller;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
public class Jsr303Controller {


    @GetMapping(value = "/jsr303/re")
    public String request(@Length(message = "name不能为空", max = 10, min = 1)@RequestParam(name = "name") String name){

        return name;
    }

}
