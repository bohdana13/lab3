package edu.redko.lab7.controller;

import edu.redko.lab7.model.Book;
import edu.redko.lab7.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
  @author User
  @project lab7
  @class BookRestController
  @version 1.0.0
  @since 24.04.2025 - 22.59
*/
@RestController
@RequestMapping("api/v1/books/")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public List<Book> showAll() {
        return bookService.getAll();
    }

    @GetMapping("{id}")
    public Book showOneById(@PathVariable String id) {
        return bookService.getById(id);
    }

    @PostMapping
    public Book insert(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping
    public Book edit(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
         bookService.delById(id);
    }
}
