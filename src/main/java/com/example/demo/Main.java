package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
    @GetMapping("/hola")
    public String saludar(){
        return "Hola, mi Primer API en SpringBoot 🚀";
    }
}
