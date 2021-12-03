/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") //localhost:8080/ configura la url que va a escuchar al controlador
public class ControladorPrincipal {
   
  
    @GetMapping("/")//localhost:8080
    public String index(){
        return "index.html";
    }
    
//    @GetMapping("/")//localhost:8080
//    public String bienvenida(ModelMap modelo) throws ErrorServicio{
//        List<Libro> libros = libroRepositorio.mostrarLibrosActivos();
//        modelo.put("titulo", libros);
////
////        List<Editorial> editoriales = editorialServicio.listarTodos();
////        modelo.put("editoriales", editoriales);
//        return "index";
//    }
//    
    

}
