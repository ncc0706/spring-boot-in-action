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

    /**
     * 新增 scala tag http://127.0.0.1/tags?name=scala
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/tags")
    public Tag save(@RequestParam(required = false, name = "name") String name) {
        return tagService.save(name);
    }

}
