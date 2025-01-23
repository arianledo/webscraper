package com.arianledo.webscraper.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "webpages")
public class Webpage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domain;
    private String url;
    private String title;
    private String picture;
    private Double rank;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Webpage(Long id, String domain, String url, String title, String description, String picture, Double rank) {
        this.id = id;
        this.domain = domain;
        this.url = url;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.rank = rank;
    }

    public Webpage() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Webpage webpage = (Webpage) o;
        return Objects.equals(id, webpage.id) && Objects.equals(domain, webpage.domain) && Objects.equals(url, webpage.url) && Objects.equals(title, webpage.title) && Objects.equals(description, webpage.description) && Objects.equals(picture, webpage.picture) && Objects.equals(rank, webpage.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, domain, url, title, description, picture, rank);
    }
}
