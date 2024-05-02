package com.guner.service;

import com.guner.entity.Book;
import com.guner.repository.BookCriteriaDao;
import com.guner.repository.BookRepository;
import com.guner.repository.JdbcProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;
    private BookCriteriaDao bookCriteriaDao;
    private JdbcProductRepository jdbcProductRepository;


    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        return optionalBook.get();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksByAuthorNameAndTitle(String authorName, String title) {
        return bookCriteriaDao.findBooksByAuthorNameAndTitle(authorName, title);
    }

    public List<Book> findBooksByAuthorNameAndTitleWithJdbc(String authorName, String title) {
        return jdbcProductRepository.findBooksByAuthorNameAndTitle(authorName, title);
    }

    public List<Book> findBooksByAuthorNameAndTitleAndPublishedDateIsToday(String authorName, String title) {
        return jdbcProductRepository.findBooksByAuthorNameAndTitleAndPublishedDateIsToday(authorName, title);
    }

    public Book updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getId()).get();
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        Book updatedBook = bookRepository.save(existingBook);
        return updatedBook;
    }

    public void deleteBook(Long BookId) {
        bookRepository.deleteById(BookId);
    }
}
