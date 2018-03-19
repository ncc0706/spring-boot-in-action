package io.arukas.controller;

import io.arukas.service.ArticleService;
import io.arukas.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/articles")
    public List<Article> list(@RequestParam(required = false, name = "title") String title){
        List<Article> list = articleService.list(title);
        return list;
    }

    @GetMapping(value = "/articles/save")
    public Article save(){
        return articleService.save();
    }

    @GetMapping(value = "/articles/{id}")
    public Article update(@PathVariable("id") String id){
        return articleService.update(id);
    }

}
