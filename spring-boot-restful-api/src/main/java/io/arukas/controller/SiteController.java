package io.arukas.controller;

import io.arukas.entity.Site;
import io.arukas.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sites")
public class SiteController {

    @Autowired
    private SiteRepository siteRepository;

    @GetMapping(value = "")
    public List<Site> sites(){
        return siteRepository.findAll();
    }

    @GetMapping("/create")
    public Site create(Site site) {
        return siteRepository.save(site);
    }

}
