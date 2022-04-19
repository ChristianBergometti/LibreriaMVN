package com.example.demo.repositorios;

import com.example.demo.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
    
    @Query("SELECT c FROM Libro c WHERE c.titulo LIKE :tituloLibro")
    public List<Libro> libroPorNombre (@Param("tituloLibro") String tituloLibro);
}
