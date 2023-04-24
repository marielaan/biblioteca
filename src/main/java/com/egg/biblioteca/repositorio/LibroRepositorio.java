
package com.egg.biblioteca.repositorio;

import com.egg.biblioteca.entidades.Libros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libros, Long>{
    
    @Query("SELECT l FROM Libros l WHERE l.titulo = :titulo")
    public Libros buscarPorTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT l FROM Libros l WHERE l.autor.nombre = :nombre")
    public List<Libros> buscarPorAutor(@Param("nombre") String nombre);
    
}
