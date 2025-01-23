package com.arianledo.webscraper.jobs;

import com.arianledo.webscraper.services.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class webscraperjob {

    @Autowired
    private SpiderService spiderService;

    @Scheduled(cron = "0 0 19 * * *")
    public void scrap() {
        spiderService.star("https://www.elpais.com");
    }
}
