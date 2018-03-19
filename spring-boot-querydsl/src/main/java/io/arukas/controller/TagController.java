package io.arukas.controller;

import io.arukas.entity.Tag;
import io.arukas.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping(value = "/tags")
    public Tag save(@RequestParam(required = false, name = "name") String name){
        return tagService.save(name);
    }

}
