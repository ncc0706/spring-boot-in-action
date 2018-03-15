package io.arukas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(value = "/demo01")
    public String demo_01(){

        return "demo 01 page";
    }

}
