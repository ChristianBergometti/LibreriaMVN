package com.example.demo.controladores;

import com.example.demo.entidades.Autor;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/crearAutor")
    public String crearAutor() {
        return "crearAutor";
    }

    @PostMapping("/persistirAutor")
    public String persistirAutor(ModelMap modelo, @RequestParam String nombre) throws ErrorServicio {
        try {
            autorServicio.crearAutor(nombre);
            modelo.put("exito", "El autor ha sido guardado!");
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
        } finally {
            return "crearAutor.html";
        }

    }

    @GetMapping("/elegirAutor")
    public String elegirAutor(ModelMap modelo) {

        modelo.put("listadoAutores", autorServicio.listarAutores());
        return "elegirAutor.html";
    }
    
    @PostMapping("/edicionAutor")
    public String edicionAutor(ModelMap modelo, @RequestParam Autor autor) throws ErrorServicio {
        
        modelo.put("nombre", autor.getNombre());
        modelo.put("id_autor", autor.getId());
        return "edicionAutor.html";
    }
    
    @PostMapping("/editarAutor") 
    public String editarAutor(ModelMap modelo, @RequestParam String nombre, @RequestParam String id) throws ErrorServicio {
        try {
            autorServicio.modificar(id, nombre);
            modelo.put("exito", "El autor fue editado con éxito!");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        } finally {
            modelo.put("nombre", nombre);
            modelo.put("id_autor", id);
            return "edicionAutor.html";
        }
    }

    
}
