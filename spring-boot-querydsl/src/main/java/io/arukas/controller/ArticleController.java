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

    /**
     * 查询所有文章
     * <p>
     * http://127.0.0.1/articles
     *
     * @param title
     * @return
     */
    @GetMapping(value = "/articles")
    public List<Article> list(@RequestParam(required = false, name = "title") String title) {
        List<Article> list = articleService.list(title);
        return list;
    }

    /**
     * 新增文章
     * <p>
     * http://127.0.0.1/articles/save?tagName=scala
     *
     * @param tagName
     * @return
     */
    @GetMapping(value = "/articles/save")
    public Article save(@RequestParam(required = false, name = "tagName") String tagName) {
        return articleService.save(tagName);
    }

    /**
     * 更新文章
     * <p>
     * http://127.0.0.1/articles/151caffb-5597-4fe9-a435-138f40e2b368?tagId=0d565c8e-ae42-483f-80a3-926178d3c045
     *
     * @param articleId
     * @param tagId
     * @return
     */
    @GetMapping(value = "/articles/{articleId}")
    public Article update(@PathVariable("articleId") String articleId,
                          @RequestParam(required = false) String tagId) {
        return articleService.update(articleId, tagId);
    }

}
