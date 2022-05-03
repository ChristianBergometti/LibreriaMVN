package com.example.demo.controladores;

import com.example.demo.errores.ErrorServicio;
import com.example.demo.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditorialControlador {
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/crearEditorial")
    public String crearEditorial() {
        return "crearEditorial";
    }
    
    @PostMapping("/persistirEditorial")
    public String persistirEditorial(ModelMap modelo, @RequestParam String nombre) throws ErrorServicio {
        try {
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito", "La editorial ha sido guardada!");
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
        }  finally {
            return "crearEditorial.html";
        }
        
        
    }
}
