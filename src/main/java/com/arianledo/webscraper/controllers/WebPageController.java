package com.arianledo.webscraper.controllers;

import com.arianledo.webscraper.entities.Webpage;
import com.arianledo.webscraper.services.SpiderService;
import com.arianledo.webscraper.services.WebpageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class WebPageController {

    @Autowired
    WebpageService webpageService;

    @Autowired
    private SpiderService spiderService;

    @GetMapping("/search")
    public List<Webpage> search(@RequestParam("query") String query) {
        return webpageService.findByText(query);
    }

    @GetMapping("/test")
    public void testSpider(@RequestParam("url") String url) {
        spiderService.star(url);
    }
}
