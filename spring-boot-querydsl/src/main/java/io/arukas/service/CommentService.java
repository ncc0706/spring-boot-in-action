package io.arukas.service;

import io.arukas.entity.Comment;
import io.arukas.repo.CommentRepository;
import io.qala.datagen.RandomValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 为指定的文章评论
     * @param articleId
     * @return
     */
    @Transactional
    public Comment save(String articleId){
        Comment comment = new Comment();
        comment.setUserId(RandomValue.length(8).alphanumeric());
        comment.setContent(RandomValue.length(10).alphanumeric());
        comment.setArticleId(articleId);
        comment = commentRepository.save(comment);
       return comment;
    }

    /**
     * 更新评论
     * @param commentId
     * @return
     */
    @Transactional
    public Comment update(String commentId){
        Comment comment = commentRepository.findOne(commentId);
        comment.setContent(RandomValue.length(200).english());
        return commentRepository.save(comment);
    }


}
