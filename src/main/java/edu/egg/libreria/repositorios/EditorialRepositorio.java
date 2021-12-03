/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.libreria.repositorios;

import edu.egg.libreria.entidades.Editorial;
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
public interface EditorialRepositorio extends JpaRepository<Editorial,String> {
 @Query("SELECT e FROM Editorial e WHERE e.alta = 1 ORDER BY e.nombre ASC")
    public List<Editorial> mostrarEditorialesActivos(); 
    
    /**
     *
     * @return
     */
    @Query("SELECT e FROM Editorial e ORDER BY e.nombre")
    public List<Editorial> mostrarTodosEditoriales();
    
    @Query("SELECT e FROM Editorial e WHERE e.id = :id")
    public Editorial buscarPorID(@Param("id")String id);
}
