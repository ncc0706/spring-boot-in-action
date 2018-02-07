package io.arukas.entity.oracle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String nickname;
}
