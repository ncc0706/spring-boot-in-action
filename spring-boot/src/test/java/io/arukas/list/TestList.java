package io.arukas.list;


import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TestList {

    public static void main(String[] args) {

        List<String> a = new ArrayList<>();

        a.add("abc");
        a.add("def");
        a.add("ff");

        List<String> b = new ArrayList<>();

        b.add("abc");
        b.add("123");
        b.add("ff");

        Collection intersection = CollectionUtils.intersection(a, b);

//        intersection.forEach(d -> {
//            System.out.println(d);
//        });


        System.out.println(b.get(new Random().nextInt(b.size())));


    }

}
