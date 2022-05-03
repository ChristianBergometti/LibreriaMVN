package com.example.demo.controladores;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.servicios.AutorServicio;
import com.example.demo.servicios.EditorialServicio;
import com.example.demo.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibroControlador {

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @Autowired
    private LibroServicio libroServicio;

    @GetMapping("/crearLibro")
    public String crearLibro(ModelMap modelo) {

        modelo.addAttribute("listadoAutores", autorServicio.listarAutores());
        modelo.addAttribute("listadoEditoriales", editorialServicio.listarEditoriales());
        modelo.addAttribute("listadoLibros", libroServicio.listarLibros());
        return "crearLibro";
    }

    @PostMapping("/persistirLibro")
    public String persistirLibro(ModelMap modelo, @RequestParam String titulo, @RequestParam String isbn, @RequestParam(required = false) Integer anio, @RequestParam(required = false) Integer ejemplares, @RequestParam(required = false) Autor autor, @RequestParam(required = false) Editorial editorial) throws ErrorServicio {
        try {
            libroServicio.crearLibro(isbn, titulo, anio, ejemplares, autor, editorial);
            modelo.put("exito", "El libro ha sido guardado!");
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
        } finally {
            modelo.addAttribute("listadoAutores", autorServicio.listarAutores());
            modelo.addAttribute("listadoEditoriales", editorialServicio.listarEditoriales());
            modelo.addAttribute("listadoLibros", libroServicio.listarLibros());
            return "crearLibro";
        }
    }
}
