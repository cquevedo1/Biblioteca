/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.libreria.repositorios;

import edu.egg.libreria.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cristian
 */
@Repository
public interface AutorRepositorio extends JpaRepository<Autor,String>{
 @Query("SELECT a FROM Autor a WHERE a.alta = 1 ORDER BY a.nombre ASC")
    public List<Autor> mostrarAutoresActivos(); 
    
     @Query("SELECT a FROM Autor a ORDER BY a.nombre ASC")
    public List<Autor> mostrarTodosAutores();
}
