package io.arukas.controller;

import io.arukas.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {

    /**
     * 登陆
     *
     * @return
     */
    @GetMapping(value = "/signin")
    public String signin() {

        if (log.isDebugEnabled()) {
            log.debug("signin");
        }
        return "account/signin";
    }

    @PostMapping(value = "/signin")
    public String signin(User user) {

        if (log.isDebugEnabled()) {
            log.debug("{}", user);
        }
        return "ok";
    }

    @GetMapping(value = "/signup")
    public String signup() {

        if (log.isDebugEnabled()) {
            log.debug("signup");
        }
        return "account/signup";
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
