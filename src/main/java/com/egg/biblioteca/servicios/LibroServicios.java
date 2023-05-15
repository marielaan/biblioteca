
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libros;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorio.AutorRepositorio;
import com.egg.biblioteca.repositorio.EditorialRepositorio;
import com.egg.biblioteca.repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicios {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    
    @Transactional
    public void crearLibros(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        
       Optional<Libros> respuesta = libroRepositorio.findById(isbn);
       Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
       Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
       
       Autor autor = new Autor();
       Editorial editorial = new Editorial();
       
       if(respuestaAutor.isPresent()){
           
           autor = respuestaAutor.get();
           
       }
       
       if(respuestaEditorial.isPresent()){
           
           editorial = respuestaEditorial.get();
       }
       
       Libros libros = new Libros();
       
       libros.setIsbn(isbn);
       libros.setTitulo(titulo);
       libros.setEjemplares(ejemplares);
       libros.setAlta(new Date());
       libros.setAutor(autor);
       libros.setEditorial(editorial);
       
       libroRepositorio.save(libros);
    
}
    
    @Transactional
  
    public List<Libros> listarLibros(){
        
        List<Libros> libros = new ArrayList();
        
        libros = libroRepositorio.findAll();
        
        return libros;
    }
    
    @Transactional
    public void modificarLibros(Long isbn, String titulo , Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libros> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        
        if(respuestaAutor.isPresent()){
            
            autor = respuestaAutor.get();
        }
        
        if(respuestaEditorial.isPresent()){
            
            editorial = respuestaEditorial.get();
        }
        
        if(respuesta.isPresent()){
            
            Libros libro = respuesta.get();
            
            libro.setTitulo(titulo);
            
            libro.setAutor(autor);
            
            libro.setEditorial(editorial);
            
            libro.setEjemplares(ejemplares);
            
            libroRepositorio.save(libro);
            
        }
    }
    
   
    public Libros getOne(Long isbn){
        return libroRepositorio.getOne(isbn);
    }
    
    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor,String idEditorial) throws MiException{
        if(isbn == null){
            throw new MiException("el isbn no puede ser nulo");
        }
        
        if(titulo.isEmpty() || titulo == null){
             throw new MiException("el titulo no puede estar vacio o nulo");
        }
         if(ejemplares == null){
            throw new MiException("el ejemplares no puede ser nulo");
        }
        
        if(idAutor.isEmpty() || idAutor == null){
             throw new MiException("el idAutor no puede estar vacio o nulo");
        }
        
         if(idEditorial.isEmpty() || idEditorial == null){
             throw new MiException("el idEditorial no puede estar vacio o nulo");
        }
    }
}
