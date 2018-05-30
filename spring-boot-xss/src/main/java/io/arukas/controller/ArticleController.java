package io.arukas.controller;

import io.arukas.entity.Article;
import io.arukas.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(value = "articles/add")
    public String publishArticle() {
        return "articles/article-add";
    }

    @PostMapping(value = "articles/add")
    public String publishArticle(Article article, ModelMap map) {
        articleRepository.save(article);
        return "redirect:/articles/list";
    }

    @GetMapping(value = "articles/list")
    public String articleList(ModelMap map) {
        List<Article> articles = articleRepository.findAll();
        map.put("articles", articles);
        return "articles/article-list";
    }

}
