package edu.redko.lab7.service;

import edu.redko.lab7.model.Book;
import edu.redko.lab7.repository.BookRepository;
import edu.redko.lab7.request.BookCreateRequest;
import edu.redko.lab7.request.BookUpdateRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            Arrays.asList(new Book("name","00001","description1"),
                    new Book("2","name2","00002","description2"),
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
    public Book create(BookCreateRequest request) {
       if (bookRepository.existsByCode(request.code())){
           return null;
       }

        Book book = mapToBook(request);
        book.setCreateDate(LocalDateTime.now());
        book.setUpdateDate(new ArrayList<LocalDateTime>());
        return bookRepository.save(book);
    }
    public Book update(Book book) {
        return bookRepository.save(book);
    }


    public void delById(String id) {
         bookRepository.deleteById(id);
    }
    private Book mapToBook(BookCreateRequest request) {
        Book book = new Book(request.name(), request.code(), request.description());
        return book;
    }

    public Book update(BookUpdateRequest request) {
        Book bookPersisted = bookRepository.findById(request.id()).orElse(null);
        if (bookPersisted != null) {
            List<LocalDateTime> updateDates = bookPersisted.getUpdateDate();
            if (updateDates == null) {
                updateDates = new ArrayList<>();
            }
            updateDates.add(LocalDateTime.now());

            Book bookToUpdate = Book.builder()
                    .id(request.id())
                    .name(request.name())
                    .code(request.code())
                    .description(request.description())
                    .createDate(bookPersisted.getCreateDate())
                    .updateDate(updateDates)
                    .build();
            return bookRepository.save(bookToUpdate);
        }
        return null;
    }

}
