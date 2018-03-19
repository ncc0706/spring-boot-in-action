package io.arukas.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.arukas.entity.Article;
import io.arukas.entity.QArticle;
import io.arukas.repo.ArticleRepository;
import io.qala.datagen.RandomValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Transactional
    public List<Article> list(String title){
        QArticle qArticle = QArticle.article;
        JPAQuery<Article> jpaQuery = jpaQueryFactory.selectFrom(qArticle);

        if(!ObjectUtils.isEmpty(title)) {
            jpaQuery.where(qArticle.title.eq(title));
        }
        List<Article> fetch = jpaQuery.fetch();
        return fetch;
    }

    @Transactional
    public Article save(){

        Article article = new Article();
        article.setTitle(RandomValue.length(10).english());
        article.setContent(RandomValue.length(12).english());
        article = articleRepository.save(article);
        return article;
    }

    public Article update(String id){
        Article article = articleRepository.findOne(id);
        article.setTitle(RandomValue.length(10).english());
        article.setContent(RandomValue.length(12).english());
        article = articleRepository.save(article);
        return article;
    }
}