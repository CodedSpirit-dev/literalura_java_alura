package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Author;
import com.aluracursos.literalura.model.Book;
import com.aluracursos.literalura.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long>{
    //Select all books
    @Query(value = "SELECT * FROM books", nativeQuery = true)
    List<Book> findAllBooks();

    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<Author> findAllAuthors();

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.birthYear <= :year AND a.deathYear >= :year")
    List<Author> findAllAuthorsLivingInYear(int year);

    @Query("SELECT b FROM Book b WHERE b.languages = :language")
    List<Book> findAllBooksByLanguages(String language);

    List<Book> findByLanguages(Languages language);
}
