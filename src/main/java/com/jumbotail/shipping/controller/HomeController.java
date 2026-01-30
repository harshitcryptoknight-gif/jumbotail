package com.jumbotail.shipping.controller;

import org.springframework.core.env.Environment;
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

    private final Environment environment;

    public HomeController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("serverStatus", "Running");
        model.addAttribute("environment", getEnvironmentName());
        model.addAttribute("currentTime", getCurrentTime());
        model.addAttribute("appVersion", "1.0.0");
        return "index";
    }

    private String getEnvironmentName() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length > 0) {
            String profile = activeProfiles[0];
            if ("prod".equalsIgnoreCase(profile) || "production".equalsIgnoreCase(profile)) {
                return "Production";
            }
        }
        return "Local";
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss"));
    }
}
