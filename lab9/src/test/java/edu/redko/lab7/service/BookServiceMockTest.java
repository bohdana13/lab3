package edu.redko.lab7.service;

import edu.redko.lab7.model.Book;
import edu.redko.lab7.repository.BookRepository;
import edu.redko.lab7.request.BookCreateRequest;
import edu.redko.lab7.request.BookUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.never;
import static org.mockito.BDDMockito.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
/*
  @author User
  @project lab7
  @class BookServiceMockTest
  @version 1.0.0
  @since 15.05.2025 - 16.25
*/class BookServiceMockTest {

    @Mock
    private BookRepository mockRepository;

    private BookService underTest;

    @Captor
    private ArgumentCaptor<Book> argumentCaptor;

    private BookCreateRequest createRequest;
    private BookUpdateRequest updateRequest;
    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new BookService(mockRepository);
    }

    @DisplayName("Create new Book. Happy Path")
    @Test
    void whenInsertNewBookAndCodeNotExistsThenSuccess() {
        createRequest = new BookCreateRequest("Little Prince", "B001", "Book Desc");
        given(mockRepository.existsByCode("B001")).willReturn(false);

        underTest.create(createRequest);

        then(mockRepository).should().save(argumentCaptor.capture());
        Book savedBook = argumentCaptor.getValue();

        assertEquals("Little Prince", savedBook.getName());
        assertEquals("B001", savedBook.getCode());
        assertNotNull(savedBook.getCreateDate());
        assertTrue(savedBook.getUpdateDate().isEmpty());
    }

    @DisplayName("Create new Book. Wrong Path")
    @Test
    void whenInsertNewBookWithExistingCodeThenReturnNull() {
        createRequest = new BookCreateRequest("Little Prince", "B001", "Book Desc");
        given(mockRepository.existsByCode("B001")).willReturn(true);

        Book result = underTest.create(createRequest);

        assertNull(result);
        verify(mockRepository, never()).save(any());
    }

    @DisplayName("ExistsByCode returns false")
    @Test
    void whenCodeDoesNotExistThenFalse() {
        given(mockRepository.existsByCode("ABC")).willReturn(false);
        boolean result = mockRepository.existsByCode("ABC");
        assertFalse(result);
    }

    @DisplayName("Create with null request throws NPE")
    @Test
    void whenCreateWithNullRequestThenThrow() {
        assertThrows(NullPointerException.class, () -> underTest.create((BookCreateRequest) null));
    }

    @Test
    void whenGetByIdExistsThenReturnBook() {
        book = new Book("1", "Book1", "B001", "Desc1");
        given(mockRepository.findById("1")).willReturn(Optional.of(book));

        Book result = underTest.getById("1");

        assertNotNull(result);
        assertEquals("Book1", result.getName());
    }

    @Test
    void whenGetByIdNotExistsThenReturnNull() {
        given(mockRepository.findById("999")).willReturn(Optional.empty());

        Book result = underTest.getById("999");

        assertNull(result);
    }

    @Test
    void whenDeleteByIdThenRepositoryCalled() {
        underTest.delById("123");

        verify(mockRepository).deleteById("123");
    }

    @DisplayName("Update book directly")
    @Test
    void whenUpdateBookThenSaveCalled() {
        book = new Book("1", "Book1", "B001", "Desc1");
        underTest.update(book);

        verify(mockRepository).save(book);
    }

    @DisplayName("Update via request. Happy Path")
    @Test
    void whenUpdateRequestValidThenBookUpdated() {
        Book persisted = new Book("1", "OldName", "B001", "OldDesc");
        persisted.setCreateDate(LocalDateTime.now().minusDays(1));
        persisted.setUpdateDate(new ArrayList<>());

        updateRequest = new BookUpdateRequest("1", "NewName", "B001", "NewDesc");

        given(mockRepository.findById("1")).willReturn(Optional.of(persisted));
        given(mockRepository.save(any(Book.class))).willAnswer(invocation -> invocation.getArgument(0)); // ✅ Fix

        Book result = underTest.update(updateRequest);

        assertNotNull(result);
        assertEquals("NewName", result.getName());
        assertEquals("NewDesc", result.getDescription());
        assertEquals(persisted.getCreateDate(), result.getCreateDate());
        assertEquals(1, result.getUpdateDate().size());
    }

    @Test
    void whenUpdateRequestNotFoundThenReturnNull() {
        updateRequest = new BookUpdateRequest("999", "Name", "Code", "Desc");
        given(mockRepository.findById("999")).willReturn(Optional.empty());

        Book result = underTest.update(updateRequest);

        assertNull(result);
    }

    @DisplayName("Create Book with null description")
    @Test
    void whenCreateBookWithNullDescriptionThenSave() {
        createRequest = new BookCreateRequest("Book", "CODE123", null);
        given(mockRepository.existsByCode("CODE123")).willReturn(false);

        Book saved = new Book("Book", "CODE123", null);
        saved.setCreateDate(LocalDateTime.now());
        saved.setUpdateDate(new ArrayList<>());
        given(mockRepository.save(any(Book.class))).willReturn(saved);

        Book created = underTest.create(createRequest);

        assertEquals("Book", created.getName());
        assertNull(created.getDescription());
        verify(mockRepository).save(any(Book.class));
    }

    @Test
    void whenCreateThenCreateDateIsSet() {
        createRequest = new BookCreateRequest("Book", "B002", "Desc");
        given(mockRepository.existsByCode("B002")).willReturn(false);

        underTest.create(createRequest);

        verify(mockRepository).save(argumentCaptor.capture());
        Book saved = argumentCaptor.getValue();

        assertNotNull(saved.getCreateDate());
        assertTrue(saved.getCreateDate().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void whenValidCreateThenNoException() {
        createRequest = new BookCreateRequest("Name", "C001", "Desc");
        given(mockRepository.existsByCode("C001")).willReturn(false);

        assertDoesNotThrow(() -> underTest.create(createRequest));
    }

    @Test
    void whenUpdateInvalidIdThenNoException() {
        updateRequest = new BookUpdateRequest("999", "Name", "C001", "Desc");
        given(mockRepository.findById("999")).willReturn(Optional.empty());

        assertDoesNotThrow(() -> underTest.update(updateRequest));
    }

    @Test
    void whenCreateThenRepositorySaveCalledOnce() {
        createRequest = new BookCreateRequest("Book", "B003", "Desc");
        given(mockRepository.existsByCode("B003")).willReturn(false);

        underTest.create(createRequest);

        verify(mockRepository, times(1)).save(any());
    }

    @Test
    void whenCreateDuplicateCodeThenRepositorySaveNotCalled() {
        createRequest = new BookCreateRequest("Book", "B004", "Desc");
        given(mockRepository.existsByCode("B004")).willReturn(true);

        underTest.create(createRequest);

        verify(mockRepository, never()).save(any());
    }

    @Test
    void whenCreateNullRequestThenThrowsNPE() {
        assertThrows(NullPointerException.class, () -> underTest.create((BookCreateRequest) null));
    }

    @Test
    void whenUpdateNullRequestThenThrowsNPE() {
        assertThrows(NullPointerException.class, () -> underTest.update((BookUpdateRequest) null));
    }

    @Test
    void whenDeleteValidIdThenNoException() {
        assertDoesNotThrow(() -> underTest.delById("123"));
        verify(mockRepository).deleteById("123");
    }

    @Test
    @DisplayName("Handle empty book list")
    void whenNoBooksThenReturnEmptyList() {
        given(mockRepository.findAll()).willReturn(Collections.emptyList());

        List<Book> result = underTest.getAll();

        assertTrue(result.isEmpty());
    }


    @Test
    @DisplayName("Create Book - update date list initialized")
    void whenCreateThenUpdateDateIsEmptyList() {
        createRequest = new BookCreateRequest("Test", "222", "test");
        given(mockRepository.existsByCode("222")).willReturn(false);

        underTest.create(createRequest);

        then(mockRepository).should().save(argumentCaptor.capture());
        Book saved = argumentCaptor.getValue();
        assertThat(saved.getUpdateDate()).isEmpty();
    }

    @Test
    @DisplayName("Update adds timestamp to history")
    void whenUpdateCalledThenAddTimestamp() {
        Book book = new Book("1", "Name", "Code", "Desc");
        book.setCreateDate(LocalDateTime.now());
        book.setUpdateDate(new ArrayList<>());
        updateRequest = new BookUpdateRequest("1", "Updated", "Code", "Updated");

        given(mockRepository.findById("1")).willReturn(Optional.of(book));
        underTest.update(updateRequest);

        then(mockRepository).should().save(argumentCaptor.capture());
        Book saved = argumentCaptor.getValue();
        assertEquals(1, saved.getUpdateDate().size());
    }



    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
    }
}