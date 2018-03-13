# demo

## validate

Validator国际化配置文件说明

1.国际化配置文件必须放在classpath的根目录下，即src/java/resources的根目录下。

2.国际化配置文件必须以ValidationMessages开头，比如ValidationMessages.properties 或者 ValidationMessages_en.properties。

3.有些代码是很难使用Validator的注解来实现，比如存在name,那么返回“该name已经存在了”。利用result.rejectValue("name", "misFormat", "该name已经存在了！");

@NotEmpty 用在集合类上面
@NotBlank 用在String上面
@NotNull    用在基本类型上

> https://www.cnblogs.com/beiyan/p/5946345.html

### swagger2 for spring boot

https://leongfeng.github.io/2017/02/20/springboot-springfox-swagger2markup-spring-restdoc/

<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.7.0</version>
</dependency>

http://127.0.0.1:4200/v2/api-docs
http://127.0.0.1:4200/swagger-ui.html

有个废弃的警告 Spring->validtion->bean validator - ...

生成静态文档

> http://mp.weixin.qq.com/s?__biz=MzAxODcyNjEzNQ==&mid=2247484764&idx=1&sn=10e4fcae5333687368960a2e64b2b70c&chksm=9bd0a8c4aca721d264e40f5f543b9f557eedf033719a97a38257835c5162ffa010c025580a65&mpshare=1&scene=23&srcid=0124apkywKZm35EYdYNPHyni#rd


## 单元测试

    @FixMethodOrder(MethodSorters.JVM)
    
    https://github.com/junit-team/junit4/wiki/test-execution-order
    
    ## VM arguments
    
    ```
    -Djava.rmi.server.hostname=$HOST_IP \
    -Dcom.sun.management.jmxremote \
    -Dcom.sun.management.jmxremote.port=$JMX_PORT \
    -Dcom.sun.management.jmxremote.rmi.port=$JMX_PORT \
    -Dcom.sun.management.jmxremote.local.only=false \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.ssl=false \
    
    ```
    
    ## docker 部署
    
    docker run --rm -p 8000:8000 -p 10086:10086 -p 1099:1099 ncc0706/spring-boot-undertow:0.0.1-SNAPSHOT
    
    ## container 内部执行
    
    一定要注意 执行命令时，这个文件【jstatd.all.policy】的位置
    ``jstatd -J-Djava.rmi.server.hostname=$HOST_IP -J-Djava.security.policy=jstatd.all.policy -p 1099``
    
    jstatd -J-Djava.rmi.server.hostname=$HOST_IP -J-Djava.security.policy=$JAVA_HOME/jstatd.all.policy -p 1099 &

