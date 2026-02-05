package com.codespire.url_shortener.Dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public class UrlRequest {

    @NotBlank(message = "URL must not be empty")
    @URL(message = "Invalid URL format")
    private String originalUrl;

    public UrlRequest(){
        
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
