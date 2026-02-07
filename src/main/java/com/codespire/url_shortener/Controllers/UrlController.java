package com.codespire.url_shortener.Controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codespire.url_shortener.Dto.UrlRequest;
import com.codespire.url_shortener.Dto.UrlResponse;
import com.codespire.url_shortener.Models.ShortUrl;
import com.codespire.url_shortener.Services.UrlService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@RestController
@Validated
public class UrlController {

    @Value("${app.base-url}")
    private String BASE_URL;
    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/generate")
    public ResponseEntity<UrlResponse> generate(@Valid @RequestBody UrlRequest request) {
        String originalUrl = request.getOriginalUrl();

        ShortUrl shortUrl = urlService.createOrGetShortUrl(originalUrl);
        String fullResponse = BASE_URL + "/" + shortUrl.getShortCode();
        return ResponseEntity.ok(new UrlResponse(fullResponse));

    }

    @GetMapping("/{shortCode:[a-zA-Z0-9]{6}}")
    public ResponseEntity<Void> redirect(@PathVariable("shortCode") String shortCode) {

        ShortUrl shortUrl = urlService.getByShortCode(shortCode);
        if (shortUrl == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(shortUrl.getOriginalUrl())).build();
    }
}
