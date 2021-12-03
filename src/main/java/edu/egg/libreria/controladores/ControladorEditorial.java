/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.libreria.controladores;

import edu.egg.libreria.Errores.ErrorServicio;
import edu.egg.libreria.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class ControladorEditorial {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
     @GetMapping("/editorial") //localhost:8080/libro/editorial
    public String formulario() {
        return "formularioEditorial.html";
    }
    
    @PostMapping("/editorial")
    public String crearEditorial(ModelMap modelo, @RequestParam(required=false) String nombre){
        try{
        editorialServicio.crearEditorial(nombre);
        modelo.put("exito", "Carga exitosa");
        return "formularioEditorial.html";
        }catch(Exception e){
            modelo.put("error", "Error faltan cargar datos");
            return "formularioEditorial";
            //throw new ErrorServicio("Faltan completar algunos datos");
        }
    }
}
