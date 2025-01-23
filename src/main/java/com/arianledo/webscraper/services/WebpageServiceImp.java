package com.arianledo.webscraper.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.arianledo.webscraper.entities.Webpage;
import com.arianledo.webscraper.repositories.WebpageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebpageServiceImp implements WebpageService {

    @Autowired
    WebpageRepository webpageRepository;

    public List<Webpage> findByText(String text) {
        return webpageRepository.findByText(text);
    }

    public void save(Webpage webpage) {
        webpageRepository.save(webpage);
    }

    public void scrapAndSave(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            String title = document.title();
            String description = document.select("meta[name=description]")
                    .attr("content");
            String picture = document.select("meta[property=og:image]")
                    .attr("content");

            URI uri = new URI(url);

            Webpage webpage = new Webpage(null, uri.getHost(), url, title, description, picture, null);

            webpageRepository.save(webpage);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<String> getAllLinksFromWebpage(String url) {
        List<String> links = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();

            List<String> linksHref = document.select("a[href]")
                    .eachAttr("abs:href");

            if (linksHref.isEmpty() || linksHref.size() == 1)
                return links;

            linksHref.remove(0);

            int i = 0;
            while (i < linksHref.size() && links.size() < 10){
                if (linksHref.get(i).endsWith("/")) {
                    linksHref.set(i, linksHref.get(i).substring(0, linksHref.get(i).length() - 1));
                }

                if ( !links.contains(linksHref.get(i)) && linksHref.get(i).length() < 250) {
                    links.add(linksHref.get(i));
                }
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return links;
    }
}
