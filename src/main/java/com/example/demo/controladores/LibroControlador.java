package com.example.demo.controladores;

import com.example.demo.servicios.AutorServicio;
import com.example.demo.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibroControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/crearLibro")
    public String crearLibro(ModelMap modelo) {
        
        modelo.addAttribute("listadoAutores", autorServicio.listarAutores());
        modelo.addAttribute("listadoEditoriales", editorialServicio.listarEditoriales());
        return "crearLibro";
    }
    
    
    
}
