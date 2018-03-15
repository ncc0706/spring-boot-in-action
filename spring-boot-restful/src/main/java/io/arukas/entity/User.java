package io.arukas.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.hibernate.annotations.UpdateTimestamp;;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(length = 120)
    private String nickname;

    private String password;

    @Column(unique = true, length = 11)
    private String mobile;

    @Column(length = 1)
    private Integer gender;

    @Column(length = 200)
    private String address;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Version
    private Long version;
}
