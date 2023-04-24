
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.EditorialServicios;
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
@RequestMapping("/editorial") //localhost:8080/editorial
public class EditorialControlador {
    
    @Autowired
    private EditorialServicios editorialServicios;
    
    @GetMapping("/registrar") //localhost:8080/editorial/registrar
    
    public String registrar(){
        return "editorial_form.html";
        
        
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) throws MiException{
        
        
        try {
            editorialServicios.crearEditorial(nombre);
            
            modelo.put("exito", "La Editorial fue registrada correctamente!!");
        } catch (MiException ex) {
           
            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
            
           
        }
        
        
        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Editorial> editoriales = editorialServicios.listarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        
        return "editorial_list";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        
        modelo.put("editorial", editorialServicios.getOne(id));
        
        return "editorial_modificar.html";
        
    }
        
}
