package io.arukas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "article")
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String title;

    private String content;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    @Version
    private Integer version;

    @ManyToMany
    private Set<Tag> tags;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
//    Fail-safe cleanup (collections) : org.hibernate.engine.loading.internal.CollectionLoadContext
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

}
