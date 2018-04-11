package io.arukas.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping(value = "/sign-in")
    public String signin() {

        if (log.isDebugEnabled()) {
            log.debug("sign-in");
        }

        return "account/signin";
    }

    @GetMapping(value = "/403")
    public String accesssDenied(Principal user, Model model) {
        if (user != null) {
            model.addAttribute("msg", "Hi 【 " + user.getName() + "】, you do not have permission to access this page!");
        } else {
            model.addAttribute("msg", "You do not have permission to access this page!");
        }
        return "error/403";
    }

}
