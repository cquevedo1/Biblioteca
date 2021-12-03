package edu.egg.libreria.controladores;

import edu.egg.libreria.Errores.ErrorServicio;
import edu.egg.libreria.entidades.Autor;
import edu.egg.libreria.entidades.Editorial;
import edu.egg.libreria.entidades.Libro;
import edu.egg.libreria.servicios.AutorServicio;
import edu.egg.libreria.servicios.EditorialServicio;
import edu.egg.libreria.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class ControladorLibro {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registro") //localhost:8080/libro/registro
    public String formulario(ModelMap modelo) {

        modelo.put("listaAutores", autorServicio.listarAutoresDeAlta());
        modelo.put("listaEditoriales", editorialServicio.listarEditorialesDeAlta());

        return "formularioLibro.html";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo, @RequestParam(required = false) Autor autor,
            @RequestParam(required = false) Editorial editorial, Long isbn,
            @RequestParam String tituloLibro, Integer anio) {

        try {
            libroServicio.crearLibro(autor, editorial, isbn, tituloLibro, anio);
            modelo.put("exito", "Libro cargado en forma exitosa!!");

        } catch (ErrorServicio e) {

            modelo.put("error", "Error faltan cargar datos");
            //throw new ErrorServicio("Error faltan cargar datos");
            //return "redirect:/libro/registro";
        }
        return "formularioLibro.html";
        //return "redirect:/libro/registro";
    }

    @GetMapping("/modificar/{id}")//PathVariable
    public String modificar(ModelMap model, @PathVariable String id, ModelMap modelo) {
        try {
            model.put("libro", libroServicio.buscarLibroPorId(id));
            return "modificar.html";
        } catch (ErrorServicio e) {
            model.put("error", "No se encontró el libro");
            return "modificar.html";
        }
    }

    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam(required = false) Autor autor,
            @RequestParam(required = false) Editorial editorial, Long isbn,
            @RequestParam String tituloLibro, Integer anio) {

        try {
            libroServicio.modificarLibro(id, autor.getNombre(), autor.getId(),
                    editorial.getNombre(), editorial.getId(), isbn, tituloLibro, anio);
            modelo.put("exito", "Modificación exitosa");
            return "listaLibros.html";
        } catch (ErrorServicio e) {
            modelo.put("error", "Faltan algunos datos");
            return "listaLibros.html";
        }
    }

    @GetMapping("/listaLibros")
    public String listaLibros(ModelMap modelo) {

        List<Libro> libros = libroServicio.listarTodosLibros();
        modelo.addAttribute("libros", libros);
        return "listaLibros.html";
    }

    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id) {
        try {
            libroServicio.bajaLibro(id);
            return "redirect:/libro/listaLibros";
        } catch (ErrorServicio e) {
            return "redirect:/";
        }
    }

    @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id) {

        try {
            libroServicio.altaLibro(id);
            return "redirect:/libro/listaLibros";
        } catch (ErrorServicio e) {
            return "redirect:/";
        }
    }

    @GetMapping("/busqueda")
    public String busqueda() {
        return "busqueda.html";
    }

}
