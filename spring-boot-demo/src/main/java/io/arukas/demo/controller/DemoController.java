package io.arukas.demo.controller;

import io.arukas.wechat.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping(value = "/demo/hello")
    public String hello(String message) {
        return demoService.say(message);
    }

}
