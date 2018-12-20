package io.arukas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/20 09:43 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
@Repository
public interface UserRepository extends JpaRepository<Employee, Integer> {
}
