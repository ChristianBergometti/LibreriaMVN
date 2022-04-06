package com.example.demo.repositorios;

import com.example.demo.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
    
    @Query("SELECT c FROM Editorial c WHERE c.nombre = :nombreEditorial")
    public List<Editorial> editorialPorNombre (@Param("nombreEditorial") String nombreEditorial);
    
    
}
