package io.arukas.repository;

import io.arukas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/11/6 14:01 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
