package com.example.demo.servicios;

import com.example.demo.entidades.Editorial;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    
    public void crearEditorial(String nombre) throws ErrorServicio {

        validar(nombre);
        
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        
        editorialRepositorio.save(editorial);

    }

    public void validar(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre de la Entidad no puede ser nulo.");
        }
    }
    
    
    public void modificar(String id, String nombre) throws ErrorServicio {
        Optional<Editorial> editorialABuscar = editorialRepositorio.findById(id);
        
        if (editorialABuscar.isPresent()) {
            Editorial editorial = editorialABuscar.get();
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("La editorial solicitada no ha sido encontrada.");
        }
    }
    
    
    public void darDeAlta(String id) throws ErrorServicio {
        Optional<Editorial> editorialABuscar = editorialRepositorio.findById(id);
        
        if (editorialABuscar.isPresent()) {
            Editorial editorial = editorialABuscar.get();
            editorial.setAlta(true);
            
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("La editorial solicitada no ha sido encontrada.");
        }
    }
    
    
    public void darDeBaja(String id) throws ErrorServicio {
        Optional<Editorial> editorialABuscar = editorialRepositorio.findById(id);
        
        if (editorialABuscar.isPresent()) {
            Editorial editorial = editorialABuscar.get();
            editorial.setAlta(false);
            
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("La editorial solicitada no ha sido encontrada.");
        }
    }
    
    public List<Editorial> listarEditoriales() {
        return editorialRepositorio.findAll();
    }
    
}
