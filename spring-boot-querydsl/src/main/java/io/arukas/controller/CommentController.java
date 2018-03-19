package io.arukas.controller;

import io.arukas.entity.Comment;
import io.arukas.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/comment")
    public Comment save(@RequestParam(name = "articleId") String articleId){
        return commentService.save(articleId);
    }

}
