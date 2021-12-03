/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.libreria.controladores;

import edu.egg.libreria.entidades.Autor;
import edu.egg.libreria.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/libro")
public class ControladorAutor {
  
     @Autowired
    private AutorServicio autorServicio;
    
     @GetMapping("/autor") //localhost:8080/libro/editorial
    public String formulario() {
        return "formularioAutor.html";
    }
    
    @PostMapping("/autor")
    public String crearAutor(ModelMap modelo, @RequestParam String nombre){
        try{
           
//            if (autorServicio.listarTodosAutores().equalsIgnoreCase(nombre)) {
//               modelo.put("error","Este Autor ya está cargado en la base de datos");
//            }else{
                autorServicio.crearAutor(nombre);
                modelo.put("exito", "Carga exitosa");
          //  }
        return "formularioAutor.html";
        }catch(Exception e){
            modelo.put("error", "Error falta cargar el nombre del Autor");
            return "formularioAutor";
           
        }
    }
    

    

    
    
}
