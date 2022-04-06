package com.example.demo.repositorios;

import com.example.demo.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
    
    @Query("SELECT c FROM Libro c WHERE c.nombre LIKE :nombreLibro")
    public List<Libro> libroPorNombre (@Param("nombreLibro") String nombreLibro);
    
    @Query("SELECT c FROM Libro c WHERE c.nombre = :nombreLibro")
    public List<Libro> libroPorPalabra (@Param("nombreLibro") String nombreLibro);
}
