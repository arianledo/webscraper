package com.arianledo.webscraper.services;

import com.arianledo.webscraper.entities.Webpage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface WebpageService {

    void save(Webpage webpage);

    List<Webpage> findByText(String text);

    void scrapAndSave(String url);

    List<String> getAllLinksFromWebpage(String url);


}
