package com.example.demo.servicios;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import com.example.demo.entidades.Libro;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.repositorios.LibroRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    public void crearLibro(String ISBN, String titulo, Integer anio, Integer ejemplares, Autor autor, Editorial editorial) throws ErrorServicio {

        validar(ISBN, titulo, anio, ejemplares, autor, editorial);

        Libro libro = new Libro();
        libro.setISBN(ISBN);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresRestantes(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setAlta(true);
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    public void validar(String ISBN, String titulo, Integer anio, Integer ejemplares, Autor autor, Editorial editorial) throws ErrorServicio {

        if (ISBN == null || ISBN.isEmpty()) {
            throw new ErrorServicio("El ISBN no puede ser nulo");
        }

        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo no puede ser nulo");
        }

        if (anio == null || anio.toString().length() > 4) {
            throw new ErrorServicio("El año no puede ser nulo o mayor de 4 dígitos");
        }

        if (autor == null) {
            throw new ErrorServicio("El autor no puede ser nulo");
        }

        if (editorial == null) {
            throw new ErrorServicio("El editorial no puede ser nulo");
        }

    }

    public void modificarLibro(String id, String ISBN, String titulo, Integer anio, Autor autor, Editorial editorial) throws ErrorServicio {
        Optional<Libro> libroABuscar = libroRepositorio.findById(id);

        if (libroABuscar.isPresent()) {
            Libro libro = libroABuscar.get();
            libro.setISBN(ISBN);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("El libro no ha sido encontrado.");
        }
    }
    
    public void darDeAlta(String id) throws ErrorServicio {
        Optional<Libro> libroABuscar = libroRepositorio.findById(id);
        if (libroABuscar.isPresent()) {
            Libro libro = libroABuscar.get();
            libro.setAlta(true);
            
            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("El libro no ha sido encontrado.");
        }
    }
    
    public void darDeBaja(String id) throws ErrorServicio {
        Optional<Libro> libroABuscar = libroRepositorio.findById(id);
        if (libroABuscar.isPresent()) {
            Libro libro = libroABuscar.get();
            libro.setAlta(false);
            
            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("El libro no ha sido encontrado.");
        }
    }
}
