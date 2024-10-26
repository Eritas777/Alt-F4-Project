package ru.profileplus.profileplusproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@RestController
public class HomeController {

    @GetMapping("/")
    public ModelAndView redirectToHome() {
        return new ModelAndView("redirect:/home.html");
    }
}
