
package com.egg.biblioteca.controladores;

//import com.egg.biblioteca.entidades.Usuario;
//import com.egg.biblioteca.servicios.UsuarioServicios;
//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    //private UsuarioServicios usuarioServicios;
    
    @GetMapping("/dashboard")
    public String panelAdministrativo(){
        return "panel.html";
        
        
    }
    
//    @GetMapping("/usuarios")
//    public String listar(ModelMap modelo){
//        //List<Usuario> usuarios = usuarioServicios.listarUsuarios();
//        modelo.addAttribute("usuarios", usuarios);
//        
//        return "usuario_list";
//    }
//    
//    @GetMapping("/modificarRol/{id}")
//    public String cambiarRol(@PathVariable String id){
//        //usuarioServicios.cambiarRol(id);
//        
//        return "redirect:/admin/usuarios";
//    }
//    
}
