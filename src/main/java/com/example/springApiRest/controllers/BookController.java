package com.example.springApiRest.controllers;

import com.example.springApiRest.entities.Book;
import com.example.springApiRest.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    // CRUD de la entidad Book

    // Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAll(){
        return this.repository.findAll();
    }

    // Buscar un libro segun su id
    // ResponseEntity sirve para devolver respuestas HTTP en funcion de la respuesta
    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        Optional<Book> bookOpt = this.repository.findById(id);
        if(bookOpt.isPresent()) // 200 OK
        {
            return ResponseEntity.ok(bookOpt.get());
        }
        else { // 404 Not Found
            return ResponseEntity.notFound().build(); //Construye una respuesta not found 404
        }
    }

    // Crear un nuevo libro en BDD
    @PostMapping("/api/book/new")
    public Book newBook (@RequestBody Book libro)
    {
        return this.repository.save(libro);
    }

    // Actualizar un libro existente
    @PutMapping("/api/book/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book toUpdate){
        if (toUpdate.getId() == null)
        {
            log.warn("Trying to update a non existing book");
            return ResponseEntity.badRequest().build();
        }
        if(!this.repository.existsById(toUpdate.getId()))
        {
            log.warn("Trying to update a non existing book");
            return ResponseEntity.notFound().build();
        }

        Book result = this.repository.save(toUpdate);
        return ResponseEntity.ok(result);
    }

    // Borrar un libro en BDD
    @DeleteMapping ("/api/book/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id)
    {
        if(!this.repository.existsById(id)) // 204 no content
        {
            log.warn("Trying to delete a non existing book");
            return ResponseEntity.notFound().build(); //Construye una respuesta not found 404
        }
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
