package com.example.demo.servicios;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import com.example.demo.entidades.Libro;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    
    
    public void crearLibro(String ISBN, String titulo, Integer anio, Integer ejemplares, Autor autor, Editorial editorial) throws ErrorServicio {

        validar(ISBN, titulo, anio, ejemplares);

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

    public void validar(String ISBN, String titulo, Integer anio, Integer ejemplares) throws ErrorServicio {

        if (ISBN == null || ISBN.isEmpty()) {
            throw new ErrorServicio("El ISBN no puede ser nulo");
        }

        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo no puede ser nulo");
        }

        if (anio == null || anio.toString().length() > 4) {
            throw new ErrorServicio("El año no puede ser nulo o mayor de 4 dígitos");
        }

        if (ejemplares <= 0 || ejemplares.toString().trim().isEmpty()) {
            throw new ErrorServicio("Los ejemplares no pueden ser 0 ni menores que 0, nulos o contener espacios.");
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
    
    
    public List<Libro> listarTodos() throws ErrorServicio { 
        
        if (libroRepositorio.findAll().isEmpty()) {
            throw new ErrorServicio("No hay libros para buscar.");
        } else {
            return libroRepositorio.findAll();
        }
    } 
    
    
    public List<Libro> buscarLibroPorNombre (String nombre) throws ErrorServicio {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorServicio("El nombre del libro no puede ser nulo.");
        } else {
            List<Libro> libroABuscar = libroRepositorio.libroPorNombre(nombre);
            
            if (libroABuscar.isEmpty()) {
                throw new ErrorServicio("No se encuentra el libro solicitado.");
            } else {
                return libroABuscar;
            }   
        }
    }
    
    
    public Libro buscarLibroPorID (String id) throws ErrorServicio {
        Optional<Libro> libroABuscar = libroRepositorio.findById(id);
        
        if (libroABuscar.isPresent()) {
            return libroABuscar.get();
        } else {
            throw new ErrorServicio("No se encuentra el libro solicitado.");
        }
    }
    
    
}
