package edu.egg.libreria.servicios;

import edu.egg.libreria.Errores.ErrorServicio;
import edu.egg.libreria.entidades.Autor;
import edu.egg.libreria.entidades.Editorial;
import edu.egg.libreria.entidades.Libro;
import edu.egg.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired
    private EditorialServicio editorialServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired //instancia los objetos, si ve que existe ese objeto en memoria lo reutiliza
    private LibroRepositorio libroRepositorio;

    //****************************CREACION******************
    @Transactional //se coloca cada vez que se almacena, elimina o modifica algún elemento de la base se debe usar Transactional
    public void crearLibro(Autor autor, Editorial editorial, Long isbn, String titulo, Integer anio) throws ErrorServicio {
      
        validacion(isbn, titulo, anio);

       // Editorial editorial = editorialServicio.buscarPorId(idEditorial);
       // Autor autor = autorServicio.buscarAutorPorId(idAutor);
        Libro libro = new Libro();
        autorServicio.buscarAutorPorId(autor.getId());
        editorialServicio.buscarPorId(editorial.getId());
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setAnio(anio);
        libro.setAlta(true);
        libro.setEditorial(editorial);
        libro.setAutor(autor);
        libroRepositorio.save(libro);
       }
    

//    public void crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares,
//            Integer ejemplaresPrestados, Integer ejemplaresRestantes, 
//            Autor autor, Editorial editorial) throws Exception {
//
//        Libro l = new Libro();
//
//        l.setIsbn(isbn);
//        l.setTitulo(titulo);
//        l.setAnio(anio);
//        l.setEjemplares(ejemplares);
//        l.setEjemplaresPrestados(ejemplaresPrestados);
//        l.setEjemplaresRestantes(ejemplaresRestantes);
//        l.setAutor(autor);
//        l.setEditorial(editorial);
//        l.setAlta(true);
//        try {
//            daoLibro.guardarLibro(l);
//         
//        } catch (Exception e) {
//            throw new Exception("Error al cargar libro en servicio");
//        }

    //******************UPDATE***********************
    @Transactional
    public void modificarLibro(String idLibro, String nombreAutor,
            String idAutor, String nombreEditorial, String idEditorial, Long isbn, String titulo, Integer anio) throws ErrorServicio {

        validacion(isbn, titulo, anio);

        Editorial editorial = editorialServicio.modificarEditorial(idEditorial, nombreEditorial);

        Autor autor = autorServicio.modificarAutor(idAutor, nombreAutor);

        Libro libro = libroRepositorio.buscarPorID(idLibro);
        if (libro!=null) {
            //Libro libro = existe.get();
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setAnio(anio);
            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio(" No existe un libro con el id solicitado");
        }

    }

    //************************BUSQUEDA O CONSULTA*******************
    @Transactional
    public Libro buscarLibroPorISBN(Long isbn) throws ErrorServicio {
        if (isbn == null || isbn < 0) {
            throw new ErrorServicio("El isbn no puede ser negativo o nulo");
        }
        return libroRepositorio.buscarPorISBN(isbn);
    }

    @Transactional
    public List<Libro> buscarLibroPorAutor(String autor) throws ErrorServicio {
        if (autor == null) {
            throw new ErrorServicio("El autor no puede estar vacío");
        }
        return libroRepositorio.buscarPorAutor(autor);
    }

    @Transactional
    public List<Libro> buscarLibroPorEditorial(String editorial) throws ErrorServicio {
        if (editorial == null) {
            throw new ErrorServicio("La editorial no puede estar vacía");
        }
        return libroRepositorio.buscarPorEditorial(editorial);
    }

    @Transactional
    public Libro buscarLibroPorId(String idLibro) throws ErrorServicio {
        Libro existeLibro = libroRepositorio.buscarPorID(idLibro);
        if (existeLibro != null) {
            return existeLibro;
           // return libro;
        } else {
            throw new ErrorServicio("No se encontró un libro con el id ingresado");
        }
    }
    
    @Transactional
    public List<Libro> listarLibrosDeAlta() throws ErrorServicio {
       return libroRepositorio.mostrarLibrosActivos();
    }
    
    @Transactional
    public List<Libro> listarTodosLibros() {
       return libroRepositorio.mostrarTodosLibros();
    }

    //***********************BAJA*****************
     @Transactional
    public void bajaLibro(String idLibro) throws ErrorServicio {
        Libro libro = libroRepositorio.buscarPorID(idLibro);
        if (libro!=null) {
           // Libro libro = existeLibro.get();
            libro.setAlta(false);
            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("No existe un libro con el id ingresado");
        }
    }

    //*******************ALTA*****************
    @Transactional
    public void altaLibro(String idLibro) throws ErrorServicio {
        Optional<Libro> existeLibro = libroRepositorio.findById(idLibro);
        if (existeLibro.isPresent()) {
            Libro libro = existeLibro.get();
            libro.setAlta(true);
            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("No existe un libro con el id ingresado");
        }
    }

    //****************VARIOS****************
    public void validacion(Long isbn, String titulo, Integer anio) throws ErrorServicio {
        if (isbn == null || isbn < 0) {
            throw new ErrorServicio("El isbn no puede ser negativo o nulo");
        }
        Libro l = libroRepositorio.buscarPorISBN(isbn);
        if (l != null) {
            throw new ErrorServicio("Este libro ya está cargado en la base de datos");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El Título del libro no debe estar vacío");
        }
    }
}
