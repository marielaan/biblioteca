package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libros;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.AutorServicios;
import com.egg.biblioteca.servicios.EditorialServicios;
import com.egg.biblioteca.servicios.LibroServicios;
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
@RequestMapping("/libro") //localhost:8080/libro
public class LibroControlador {

    @Autowired
    private LibroServicios libroServicios;
    @Autowired
    private AutorServicios autorServicios;
    @Autowired
    private EditorialServicios editorialServicios;

    @GetMapping("/registrar") //localhost:8080/libro/registrar

    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicios.listarAutores();
        List<Editorial> editoriales = editorialServicios.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
            @RequestParam String idEditorial, ModelMap modelo) throws MiException {

        try {
            libroServicios.crearLibros(isbn, titulo, ejemplares, idAutor, idEditorial); //si todo sale bien retornamos al index

            modelo.put("exito", "El libro fue cargado correctamente!!");
        } catch (MiException ex) {
            List<Autor> autores = autorServicios.listarAutores();
            List<Editorial> editoriales = editorialServicios.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            modelo.put("error", ex.getMessage());
            
            return "libro_form.html"; //volvemos a cargar el formulario

        }

        return "index.html";

    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Libros> libros = libroServicios.listarLibros();
        modelo.addAttribute("libros", libros);
        
        return "libros_list";
    }
    
    @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo){
        
        modelo.put("libro", libroServicios.getOne(isbn));
        
        List<Autor> autores = autorServicios.listarAutores();
        List<Editorial> editoriales = editorialServicios.listarEditoriales();
        
        
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        
        return "libro_modificar.html";
        
    }
    
    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo , Integer ejemplares, String idAutor, String idEditorial, ModelMap modelo){
        
        try {
        
            
           
            
            libroServicios.modificarLibros(isbn, titulo, ejemplares, idAutor, idEditorial);
            
            return "redirect:/libro/lista";
            
        } catch (MiException ex) {
            List<Autor> autores = autorServicios.listarAutores();
            List<Editorial> editoriales = editorialServicios.listarEditoriales();
            
            modelo.put("EL ERROR ES ACA", ex.getMessage());
            
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            
            return "libro_modificar.html";
        }
    }

}
