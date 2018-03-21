package io.arukas.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.arukas.entity.Article;
import io.arukas.entity.QArticle;
import io.arukas.entity.Tag;
import io.arukas.repo.ArticleRepository;
import io.arukas.repo.TagRepository;
import io.qala.datagen.RandomValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    /**
     * 查询文章
     * @param title
     * @return
     */
    public List<Article> list(String title){
        QArticle qArticle = QArticle.article;
        JPAQuery<Article> jpaQuery = jpaQueryFactory.selectFrom(qArticle);

        if(!ObjectUtils.isEmpty(title)) {
            jpaQuery.where(qArticle.title.eq(title));
        }
        List<Article> fetch = jpaQuery.fetch();
        return fetch;
    }

    /**
     * 保存文章
     * @param tagName
     * @return
     */
    @Transactional
    public Article save(String tagName){

        Article article = new Article();
        article.setTitle(RandomValue.length(10).english());
        article.setContent(RandomValue.length(12).english());

        Set<Tag> tags = new HashSet<>();
        Tag tag = tagRepository.findByTagName(tagName);
        tags.add(tag);
        article.setTags(tags);

        article = articleRepository.save(article);
        return article;
    }

    /**
     * 更新文章
     * @param articleId
     * @param tagId
     * @return
     */
    public Article update(String articleId, String tagId){

        Article article = articleRepository.findOne(articleId);
        article.setTitle(RandomValue.length(10).english());
        article.setContent(RandomValue.length(12).english());

        // tagId不为空则更新tag
        if(!ObjectUtils.isEmpty(tagId)){
            Set<Tag> tags = new HashSet<Tag>();
            Tag tag = tagRepository.findOne(tagId);
            tags.add(tag);
            article.setTags(tags);
        }

        article = articleRepository.save(article);
        return article;
    }
}
