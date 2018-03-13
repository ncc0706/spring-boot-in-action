package io.arukas.controller;

import com.alibaba.fastjson.JSON;
import io.arukas.entity.User;
import io.qala.datagen.RandomValue;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserController Tester.
 *
 * @author <niuyuxian@163.com>
 * @version 1.0
 * @since <pre> 2018</pre>
 */

@SpringBootTest
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void after() throws Exception {

    }


    @Test
    public void testUser() throws Exception {

        User user = TestUserData.user();
        String content = JSON.toJSONString(user);
        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.username").value(user.getUsername()));
    }


    static class TestUserData {

        public static User user(){

            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setNickname(RandomValue.length(10).english());
            user.setUsername(RandomValue.length(6).alphanumeric());
            return user;
        }

    }

}
