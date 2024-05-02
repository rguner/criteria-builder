package com.guner.repository;

import com.guner.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcProductRepository {

    private final JdbcTemplate jdbcTemplate;


    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", this::mapRowToBook);
    }

    public Book findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id=?", this::mapRowToBook, id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(author, title) VALUES (?, ?)", book.getAuthor(), book.getTitle());
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE book SET author=?, title=? WHERE id=?", book.getAuthor(),
                book.getTitle(), book.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setAuthor(rs.getString("author"));
        book.setTitle(rs.getString("title"));
        book.setPublishedDate(rs.getTimestamp("published_date"));
        return book;
    }

    public List<Book> findBooksByAuthorNameAndTitle(String authorName, String title) {
        String findBooksByAuthorNameAndTitleSql = "SELECT * FROM book WHERE author = ? AND title LIKE ?";
        return jdbcTemplate.query(findBooksByAuthorNameAndTitleSql, this::mapRowToBook,
                authorName, title);
    }

    public List<Book> findBooksByAuthorNameAndTitleAndPublishedDateIsToday(String authorName, String title) {
        String findBooksByAuthorNameAndTitleSql = "SELECT * FROM book WHERE author = ? AND title LIKE ? " +
                "and published_date  >= CURRENT_DATE AND published_date < CURRENT_DATE + INTERVAL '1 day'";
        return jdbcTemplate.query(findBooksByAuthorNameAndTitleSql, this::mapRowToBook,
                authorName, title);
    }
}