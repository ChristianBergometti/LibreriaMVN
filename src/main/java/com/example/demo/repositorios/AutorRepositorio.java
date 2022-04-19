package com.example.demo.repositorios;

import com.example.demo.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    
    @Query("SELECT c FROM Autor c WHERE c.nombre = :nombreAutor")
    public List<Autor> autorPorNombre (@Param("nombreAutor") String nombreAutor);
}
