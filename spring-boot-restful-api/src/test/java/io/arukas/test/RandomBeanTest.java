package io.arukas.test;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.arukas.model.User;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import io.github.benas.randombeans.randomizers.text.StringRandomizer;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.benas.randombeans.FieldDefinitionBuilder.field;

public class RandomBeanTest {

    @Test
    public void test01(){

        Stream<User> stream = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(field().named("nickname").ofType(String.class).inClass(User.class).get(),
                        new StringRandomizer(5, 30, 10))
                .build()
                .objects(User.class, 20);
        stream.forEach(user -> {
            System.out.println(user.getNickname().length());
        });
    }

    @Test
    public void test02(){

        List<User> users = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(field().named("nickname").ofType(String.class).inClass(User.class).get(),
                        new StringRandomizer(5, 30, 10))
                .build()
                .objects(User.class, 20)
                .collect(Collectors.toList());

    }

}
