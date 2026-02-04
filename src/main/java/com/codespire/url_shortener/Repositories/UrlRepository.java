package com.codespire.url_shortener.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespire.url_shortener.Models.ShortUrl;

@Repository
public interface UrlRepository extends JpaRepository<ShortUrl, Long> {
    
    Optional<ShortUrl> findByShortCode(String shortCode);
    Optional<ShortUrl> findByOriginalUrl(String originalUrl);
    boolean existsByShortCode(String shortCode);
}
