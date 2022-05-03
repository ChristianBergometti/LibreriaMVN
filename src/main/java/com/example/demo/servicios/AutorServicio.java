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
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo.");

        } else {
            if (!autorRepositorio.autorPorNombre(nombre).isEmpty()) {
                throw new ErrorServicio("El autor ya existe en la base de datos.");
            }
        }
    }

    public void modificar(String id, String nombre) throws ErrorServicio {
        Autor autor = autorRepositorio.findById(id).get();
        autor.setNombre(nombre);
        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo.");
        } else {
            autorRepositorio.save(autor);
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

    public Autor autorPorID(String id) throws ErrorServicio {
        Optional<Autor> autorABuscar = autorRepositorio.findById(id);
        if (autorABuscar.isPresent()) {
            return autorABuscar.get();
        } else {
            throw new ErrorServicio("El autor no ha sido encontrado.");
        }
    }

    public Autor autorPorNombre(String nombre) throws ErrorServicio {
        List<Autor> autorABuscar = autorRepositorio.autorPorNombre(nombre);
        if (!autorABuscar.isEmpty()) {
            return autorABuscar.get(0);
        } else {
            throw new ErrorServicio("El autor no ha sido encontrado.");
        }
    }
}
