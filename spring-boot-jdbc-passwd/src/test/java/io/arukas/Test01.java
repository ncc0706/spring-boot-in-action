package io.arukas;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/11/6 17:24 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
public class Test01 {

    public static void main(String[] args) {
        //PBEWithMD5AndDES
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("J3l9oryQmwYk60H1h28VdzceDqbCZ5sifPAnkOLuSUvKWBXCag47M");
        String encrypted = encryptor.encrypt("jdbc:mysql://47.52.149.71:3306/k2d?useSSL=false&createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8");
        System.out.println(encrypted);


        /*
        //加密工具
        StandardPBEStringEncryptor encryptor2 = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        //自己在用的时候更改此密码
        config.setPassword("apdplat");
        //应用配置
        encryptor2.setConfig(config);
        String ciphertext = "azL9Cyp9H62r3eUgZ+TESw==";
        //解密
        String plaintext = encryptor2.decrypt(ciphertext);
        System.out.println(ciphertext + " : " + plaintext);
        */

    }


}
