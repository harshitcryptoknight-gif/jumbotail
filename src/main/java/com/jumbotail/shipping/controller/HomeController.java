package com.jumbotail.shipping.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller for serving the landing page.
 */
@Controller
public class HomeController {

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("serverStatus", "Running");
        model.addAttribute("environment", getEnvironmentName());
        model.addAttribute("currentTime", getCurrentTime());
        model.addAttribute("appVersion", "1.0.0");
        return "index";
    }

    private String getEnvironmentName() {
        if ("prod".equalsIgnoreCase(activeProfile) || "production".equalsIgnoreCase(activeProfile)) {
            return "Production";
        }
        return "Local";
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss"));
    }
}
