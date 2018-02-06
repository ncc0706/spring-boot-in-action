package io.arukas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @GetMapping(value = "/01")
    public String index_01(){
        return "/demo/01";
    }

}
