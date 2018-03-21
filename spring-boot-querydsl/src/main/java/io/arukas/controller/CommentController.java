package io.arukas.controller;

import io.arukas.entity.Comment;
import io.arukas.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 为文章新增评论
     * <p>
     * http://127.0.0.1/comment?articleId=151caffb-5597-4fe9-a435-138f40e2b368
     *
     * @param articleId
     * @return
     */
    @GetMapping(value = "/comment")
    public Comment save(@RequestParam(name = "articleId") String articleId) {
        return commentService.save(articleId);
    }

    /**
     * 修改评论信息
     * <p>
     * http://127.0.0.1/comment/df69ede1-1c3b-4914-91a1-737ecb4bbd81
     *
     * @param commentId
     * @return
     */
    @GetMapping(value = "/comment/{commentId}")
    public Comment update(@PathVariable("commentId") String commentId) {
        return commentService.update(commentId);
    }

}
