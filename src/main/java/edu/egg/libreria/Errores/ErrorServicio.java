/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.libreria.Errores;

/**
 *
 * @author Cristian
 */
public class ErrorServicio extends Exception{
    
    public ErrorServicio(String msn){
        super(msn);
    }
}
