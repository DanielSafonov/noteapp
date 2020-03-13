package com.safonov.demo.application.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TechController {
    @GetMapping(value = "/ping")
    public String pong() {
        return "pong";
    }
}
