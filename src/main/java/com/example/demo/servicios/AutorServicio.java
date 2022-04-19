package com.example.demo.servicios;

import com.example.demo.entidades.Autor;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    public void crearAutor(String nombre) throws ErrorServicio {
        validar(nombre);
        
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);
        
        autorRepositorio.save(autor);
    }
    
    public void validar(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo.");
            
        }
    }
    
    public void modificar(String id, String nombre) throws ErrorServicio {
        Optional<Autor> autorABuscar = autorRepositorio.findById(id);
        
        if (autorABuscar.isPresent()) {
            Autor autor = autorABuscar.get();
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("El autor no ha sido encontrado.");
        }
    }
    
    public void darDeAlta(String id) throws ErrorServicio {
        Optional<Autor> autorABuscar = autorRepositorio.findById(id);
        if (autorABuscar.isPresent()) {
            Autor autor = autorABuscar.get();
            autor.setAlta(true);
            
            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("El autor no ha sido encontrado.");
        }
    }
    
    public void darDeBaja(String id) throws ErrorServicio {
        Optional<Autor> autorABuscar = autorRepositorio.findById(id);
        if (autorABuscar.isPresent()) {
            Autor autor = autorABuscar.get();
            autor.setAlta(false);
            
            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("El autor no ha sido encontrado.");
        }
    }
    
    public List<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }
}
