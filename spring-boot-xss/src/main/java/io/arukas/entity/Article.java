package io.arukas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author niuyuxian
 */
@Data
@Entity
@Table(name = "article")
@EntityListeners(AuditingEntityListener.class)
public class Article implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    // 文章标题
    @Column(nullable = false, name = "title", length = 255)
    private String title;

    // 文章内容
    @Column(name = "content", length = 200)
    private String content;

    private Integer num;

    // 文章状态 0：未发布, 1：已发布
    private Integer state;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @Version
    private Long version;

}
