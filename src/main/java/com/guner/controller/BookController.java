package com.guner.controller;

import com.guner.entity.Book;
import com.guner.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/books")
public class BookController {

    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/Books/1
    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long bookId){
        Book Book = bookService.getBookById(bookId);
        return new ResponseEntity<>(Book, HttpStatus.OK);
    }

    // http://localhost:8080/api/Books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("{id}")
    // http://localhost:8080/api/Books/1
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long bookId,
                                           @RequestBody Book book){
        book.setId(bookId);
        Book updatedBook = bookService.updateBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book successfully deleted!", HttpStatus.OK);
    }

    @GetMapping("/findBooksByAuthorNameAndTitle")
    public ResponseEntity<List<Book>> findBooksByAuthorNameAndTitle(@RequestParam("author") String author,
                                                                    @RequestParam("title") String title) {
        List<Book> books = bookService.findBooksByAuthorNameAndTitle(author, title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/findBooksByAuthorNameAndTitleWithJdbc")
    public ResponseEntity<List<Book>> findBooksByAuthorNameAndTitleWithJdbc(@RequestParam("author") String author,
                                                                    @RequestParam("title") String title) {
        List<Book> books = bookService.findBooksByAuthorNameAndTitleWithJdbc(author, title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/findBooksByAuthorNameAndTitleAndPublishedDateIsToday")
    public ResponseEntity<List<Book>> findBooksByAuthorNameAndTitleAndPublishedDateIsToday(@RequestParam("author") String author,
                                                                            @RequestParam("title") String title) {
        List<Book> books = bookService.findBooksByAuthorNameAndTitleAndPublishedDateIsToday(author, title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


}
