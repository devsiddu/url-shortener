package com.codespire.url_shortener.Dto;

public class UrlResponse {
    
    private String shortUrl;

    public UrlResponse(String shortUrl){
        this.shortUrl = shortUrl;
    }

    public String getShortUrl(){
        return shortUrl;
    }
}
