
package edu.egg.libreria.repositorios;

import edu.egg.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cristian
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro,String>{
    //es un repositorio de Libro y la llave primaria de libro es de tipo String
    
    @Query("SELECT l FROM Libro l WHERE l.isbn = :isbn")
    public Libro buscarPorISBN(@Param("isbn") Long isbn );
    
    @Query("SELECT l FROM Libro l ORDER BY l.titulo")
    public List<Libro> mostrarTodosLibros();
    
     @Query("SELECT l FROM Libro l WHERE l.alta = true ORDER BY l.titulo ASC")
    public List<Libro> mostrarLibrosActivos(); 
//    
//    @Query("SELECT ejemplaresprestados FROM Libro l WHERE l.titulo = :titulo")
//    public Integer prestados(@Param("titulo") String titulo);
//    
//    @Query("SELECT ejemplaresrestantes FROM Libro l WHERE l.titulo = :titulo")
//    public Integer restantes(@Param("titulo") String titulo);
//    
    @Query("SELECT l FROM Libro l WHERE l.editorial.nombre = :nombre ORDER BY l.titulo")
    public List<Libro> buscarPorEditorial(@Param("nombre")String nombre);
    
    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre ORDER BY l.titulo")
    public List<Libro> buscarPorAutor(@Param("nombre")String nombre);
    
     @Query("SELECT l FROM Libro l WHERE l.id = :id")
    public Libro buscarPorID(@Param("id")String id);
}
