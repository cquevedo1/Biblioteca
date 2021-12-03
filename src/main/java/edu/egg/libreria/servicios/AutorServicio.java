package edu.egg.libreria.servicios;

import edu.egg.libreria.Errores.ErrorServicio;
import edu.egg.libreria.entidades.Autor;
import edu.egg.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    //*****************CREACION***********************
    @Transactional
    public void crearAutor(String nombre) throws ErrorServicio {

        validacion(nombre);

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);

        autorRepositorio.save(autor);
    }

    //*******************UPDATE*****************
    @Transactional
    public Autor modificarAutor(String id, String nombre) throws ErrorServicio {

        validacion(nombre);

        Optional<Autor> existe = autorRepositorio.findById(id); // busca el autor por id
        if (existe.isPresent()) {
            Autor autor = existe.get();
            autor.setNombre(nombre); //modifica el nombre del autor encontrado
            return autorRepositorio.save(autor);
           
        } else {
            throw new ErrorServicio("No se encontró el Autor que desea modificar");
        }
    }

    //******************CONSULTA*****************
    @Transactional
    public Autor buscarAutorPorId(String idAutor) throws ErrorServicio {
        Optional<Autor> existeAutor = autorRepositorio.findById(idAutor);
        if (existeAutor.isPresent()) {
            return existeAutor.get();

        } else {
            throw new ErrorServicio("No se encontró el Autor del libro que desea ingresar");
        }

    }

    @Transactional
    public List<Autor> listarTodosAutores() {
        List<Autor> listaAutores=  autorRepositorio.mostrarTodosAutores();
        return listaAutores;
    }

    @Transactional
    public List<Autor> listarAutoresDeAlta() {
        return autorRepositorio.mostrarAutoresActivos();
    }

    //**********************BAJA***************
    @Transactional
    public void deshabilitarAutor(String id) throws ErrorServicio {
        Optional<Autor> existe = autorRepositorio.findById(id); // busca el autor por id
        if (existe.isPresent()) {
            Autor autor = existe.get();
            autor.setAlta(false); //modifica el estado del autor encontrado

            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("No se encontró el Autor que desea deshabilitar");
        }
    }

    //*****************ALTA********************
    @Transactional
    public void habilitarAutor(String id) throws ErrorServicio {
        Optional<Autor> existe = autorRepositorio.findById(id); // busca el autor por id
        if (existe.isPresent()) {
            Autor autor = existe.get();
            autor.setAlta(true); //modifica el estado del autor encontrado

            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("No se encontró el Autor que desea habilitar");
        }
    }

    //******************VARIOS**************
    public void validacion(String nombre) throws ErrorServicio {

        Autor autor = new Autor();
//        if (autor.getNombre().equalsIgnoreCase(nombre)) {
//            throw new ErrorServicio("Este Autor ya está cargado en la base de datos");
//        }
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del Autor no debe estar vacío");
        }
    }
}
