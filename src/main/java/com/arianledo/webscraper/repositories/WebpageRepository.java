package com.arianledo.webscraper.repositories;

import com.arianledo.webscraper.entities.Webpage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebpageRepository extends CrudRepository<Webpage, Long> {

    @Query("SELECT w FROM Webpage w WHERE w.domain LIKE %:text% OR  w.url LIKE %:text% OR w.title LIKE %:text% OR w.description LIKE %:text% ORDER BY w.rank DESC")
    List<Webpage> findByText(@Param("text") String text);

    Webpage findByUrl(String url);
}
