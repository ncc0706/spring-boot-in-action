package io.arukas;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJdbcPasswdApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void contextLoads() {

        String encrypt = stringEncryptor.encrypt("123456");
        System.out.println(encrypt);

        encrypt = "woFhcgipNseuXdimXhQvUg==";
        String decrypt = stringEncryptor.decrypt(encrypt);
        System.out.println(decrypt);
    }

}
