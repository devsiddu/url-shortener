package com.codespire.url_shortener.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codespire.url_shortener.Dto.UrlUiRequest;

@Controller
public class PageController {
    
    @GetMapping("/")
    public String home(Model model){
        if(!model.containsAttribute("urlUiRequest")){
            model.addAttribute("urlUiRequest", new UrlUiRequest());
        }
        return "index";
    }
}
