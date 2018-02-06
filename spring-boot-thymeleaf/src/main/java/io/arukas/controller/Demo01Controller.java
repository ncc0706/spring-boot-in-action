package io.arukas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/demo01")
public class Demo01Controller {

    @GetMapping(value = "/")
    public String index(){
        return "/demo/01";
    }

}
