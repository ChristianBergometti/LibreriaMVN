package com.example.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexControlador {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
}
