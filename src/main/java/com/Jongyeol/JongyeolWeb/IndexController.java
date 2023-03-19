package com.Jongyeol.JongyeolWeb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String mainMenu() {
        return "집에가고싶다";
    }
}
