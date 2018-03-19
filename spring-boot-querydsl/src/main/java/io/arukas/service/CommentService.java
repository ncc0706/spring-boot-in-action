package io.arukas.service;

import io.arukas.entity.Article;
import io.arukas.entity.Comment;
import io.arukas.repo.ArticleRepository;
import io.arukas.repo.CommentRepository;
import io.qala.datagen.RandomValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public Comment save(String articleId){
        Article article = articleRepository.findOne(articleId);
        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setUserid(RandomValue.length(8).alphanumeric());
        comment.setContent(RandomValue.length(10).alphanumeric());
       return commentRepository.save(comment);
    }


}
