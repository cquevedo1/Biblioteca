package edu.egg.libreria.servicios;

import edu.egg.libreria.Errores.ErrorServicio;
import edu.egg.libreria.entidades.Editorial;
import edu.egg.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    //************************CREACION****************
    @Transactional
    public void crearEditorial(String nombre) throws ErrorServicio {

        validacion(nombre);

        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);

        editorialRepositorio.save(editorial);
    }

    //**************************UPDATE*******************
    @Transactional
    public Editorial modificarEditorial(String id, String nombre) throws ErrorServicio {

        validacion(nombre);

        Editorial editorial = editorialRepositorio.buscarPorID(id);
        if (editorial != null) {
            //Editorial editorial = existe.get();
            editorial.setNombre(nombre);
            return editorialRepositorio.save(editorial);
           
        } else {
            throw new ErrorServicio("No se encontró la editorial que desea modificar");
        }
    }

    //**********************BUSQUEDA O CONSULTA******************
    @Transactional
    public Editorial buscarPorId(String idEditorial) throws ErrorServicio {
        Optional<Editorial> existeEditorial = editorialRepositorio.findById(idEditorial);
        if (existeEditorial.isPresent()) {
            return existeEditorial.get();

        } else {
            throw new ErrorServicio("No se encontró la editorial que desea ingresar");
        }
    }

    @Transactional
    public List<Editorial> listarEditorialesDeAlta() {
        return editorialRepositorio.mostrarEditorialesActivos();
    }

    @Transactional
    public List<Editorial> listarTodasEditoriales() {
        return editorialRepositorio.mostrarTodosEditoriales();
    }

    //********************BAJA**********************
    @Transactional
    public void deshabilitarEditorial(String id) throws ErrorServicio {
        Optional<Editorial> existe = editorialRepositorio.findById(id);
        if (existe.isPresent()) {
            Editorial editorial = existe.get();
            editorial.setAlta(false);
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("No se encontró la editorial que desea deshabilitar");
        }
    }

    //************************ALTA***********************
    @Transactional
    public void habilitarEditorial(String id) throws ErrorServicio {
        Optional<Editorial> existe = editorialRepositorio.findById(id);
        if (existe.isPresent()) {
            Editorial editorial = existe.get();
            editorial.setAlta(true);
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("No se encontró la editorial que desea habilitar");
        }
    }

    //**************VARIOS*****************
    
    public void validacion(String nombre) throws ErrorServicio {

        Editorial editorial = new Editorial();
//        if (editorial.getNombre().equalsIgnoreCase(nombre)) {
//            throw new ErrorServicio("Esta editorial ya está cargada en la base de datos");
//        }
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre de la editorial no debe estar vacío");
        }
    }
}
