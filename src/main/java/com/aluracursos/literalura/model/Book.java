package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors;
    @Enumerated(EnumType.STRING)
    private Languages languages;
    private Integer downloads;

    public Book() {
    }

    public Book ( List<BooksData> results ) {
    }

    public Book (String title, List<String> languages, Integer downloads, List<AuthorsData> authors ) {
        this.title = title;
        this.languages = Languages.fromString(languages.get(0));
        this.downloads = downloads;
        this.authors = new ArrayList<>();
        for (AuthorsData authorsData : authors) {
            Author author = new Author(authorsData.name(), authorsData.birthYear(), authorsData.deathYear(), this);
            this.authors.add(author);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }
}