package com.codespire.url_shortener.Services;

import org.springframework.stereotype.Service;

import com.codespire.url_shortener.Models.ShortUrl;
import com.codespire.url_shortener.Repositories.UrlRepository;
import com.codespire.url_shortener.Utils.ShortCodeGenerator;

@Service
public class UrlService {

    private final UrlRepository repository;
    private final ShortCodeGenerator shortCodeGenerator;

    public UrlService(
            UrlRepository repository,
            ShortCodeGenerator shortCodeGenerator) {
        this.repository = repository;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    public ShortUrl createOrGetShortUrl(ShortUrl request) {

        return repository
                .findByOriginalUrl(request.getOriginalUrl())
                .orElseGet(() -> {
                    ShortUrl shortUrl = new ShortUrl();
                    shortUrl.setOriginalUrl(request.getOriginalUrl());

                    String code;
                    do {
                        code = shortCodeGenerator.generate();
                    } while (repository.existsByShortCode(code));

                    shortUrl.setShortCode(code);
                    return repository.save(shortUrl);
                });
    }

    public ShortUrl getByShortCode(String shortCode) {
        return repository.findByShortCode(shortCode).orElse(null);
    }
}
