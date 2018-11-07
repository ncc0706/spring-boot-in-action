package io.arukas.endpoint;

import io.arukas.entity.User;
import io.arukas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/11/6 14:00 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
@RestController
public class UserEndpoint {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public User create(User user) {
        return userRepository.save(user);
    }

}
