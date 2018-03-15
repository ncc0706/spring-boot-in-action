package io.arukas.controller;

import com.alibaba.fastjson.JSON;
import io.arukas.entity.User;
import io.qala.datagen.RandomValue;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
@Slf4j
@Transactional
@SpringBootTest
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs(outputDir = "build/asciidoc/snippets")
public class UserControllerTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private MockMvc mvc;

//    @BeforeEach
//    public void setUp(WebApplicationContext context,
//                      RestDocumentationContextProvider restDocumentation) throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(context)
//                .apply(documentationConfiguration(restDocumentation))
//                .build();
//    }

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @After
    public void after() throws Exception {

    }


    /**
     * @see {@link https://github.com/spring-projects/spring-restdocs/blob/v1.2.1.RELEASE/samples/rest-notes-spring-data-rest/src/test/java/com/example/notes/ApiDocumentation.java}
     * @throws Exception
     */
    @Test
    public void testUser() throws Exception {

        User user = TestUserData.user();
        String content = JSON.toJSONString(user);
        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andDo(print())
                // Spring Rest Docs 生成 Asciidoc 片段
                .andDo(document("add-user", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk()).andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.username").value(user.getUsername())).andReturn();
    }


    @Test
    public void createSpringFoxSwaggerJson() throws Exception {
        //String designFirstSwaggerLocation = Swagger2MarkupTest.class.getResource("/swagger.yaml").getPath();
        String outputDir = System.getProperty("io.springfox.staticdocs.outputDir"); // mvn test
//        String outputDir = "D:\\workspace\\springfox-swagger2-demo\\target\\swagger"; // run as
        log.info("--------------------outputDir: {}--------------------", outputDir);
        MvcResult mvcResult = this.mvc.perform(get("/v2/api-docs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String swaggerJson = response.getContentAsString();
        Files.createDirectories(Paths.get(outputDir));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8)) {
            writer.write(swaggerJson);
        }
        log.info("--------------------swaggerJson create --------------------");
    }


    static class TestUserData {

        public static User user() {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setNickname(RandomValue.length(10).english());
            user.setUsername(RandomValue.length(6).alphanumeric());
            return user;
        }

        public String strUser() {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setNickname(RandomValue.length(10).english());
            user.setUsername(RandomValue.length(6).alphanumeric());
            return JSON.toJSONString(user);
        }

    }

}
