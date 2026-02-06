package com.codespire.url_shortener.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codespire.url_shortener.Dto.UrlUiRequest;
import com.codespire.url_shortener.Models.ShortUrl;
import com.codespire.url_shortener.Services.UrlService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Controller
public class UrlUiController {

    @Value("${app.base-url}")
    private String BASE_URL;
    private UrlService service;

    public UrlUiController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/generate-ui")
    public String generate(
            @Valid UrlUiRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.urlUiRequest",
                    bindingResult);
            redirectAttributes.addFlashAttribute("urlUiRequest", request);
            return "redirect:/";
        }

        ShortUrl shortUrl = service.createOrGetShortUrl(request.getOriginalUrl());
        redirectAttributes.addFlashAttribute(
                "shortUrl",
                BASE_URL + "/" + shortUrl.getShortCode());

        return "redirect:/";
    }

}
