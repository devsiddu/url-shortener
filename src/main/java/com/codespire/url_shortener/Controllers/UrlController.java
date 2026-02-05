package com.codespire.url_shortener.Controllers;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codespire.url_shortener.Dto.UrlRequest;
import com.codespire.url_shortener.Dto.UrlResponse;
import com.codespire.url_shortener.Models.ShortUrl;
import com.codespire.url_shortener.Services.UrlService;

@RestController
public class UrlController {

    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/generate")
    public ResponseEntity<UrlResponse> generate(@RequestBody UrlRequest request) {
        String originalUrl = request.getOriginalUrl();

        if(!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")){
            originalUrl = "https://" + originalUrl;
        }
        ShortUrl shortUrl = urlService.createOrGetShortUrl(originalUrl);
        String fullResponse = "http://localhost:8080/" + shortUrl.getShortCode();
        return ResponseEntity.ok(new UrlResponse(fullResponse));

    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable("shortCode") String shortCode) {

        ShortUrl shortUrl = urlService.getByShortCode(shortCode);
        if (shortCode == null) {
            return ResponseEntity.notFound().build();
        }

        
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(shortUrl.getOriginalUrl())).build();
    }
}
