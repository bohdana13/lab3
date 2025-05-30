package edu.redko.lab7.service;

import edu.redko.lab7.model.Book;
import edu.redko.lab7.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
  @author User
  @project lab7
  @class BookService
  @version 1.0.0
  @since 24.04.2025 - 22.25
*/


@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    private List<Book> books = new ArrayList<>(
            Arrays.asList(new Book("1","namwe","00001","description1"),
                    new Book("2","namwe2","00002","description2"),
            new Book("3","namwe3","00003","description3"))
    );

    @PostConstruct
    void init() {
         bookRepository.deleteAll();
         bookRepository.saveAll(books);
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }


    public void delById(String id) {
         bookRepository.deleteById(id);
    }

}
