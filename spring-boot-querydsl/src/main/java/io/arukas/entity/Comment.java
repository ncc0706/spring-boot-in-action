package io.arukas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String userid;

    private String content;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    @ManyToOne
    private Article article;

    @Version
    private Integer version;
}
