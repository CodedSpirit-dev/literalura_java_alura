package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;


@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private List<String> authors;
    private List<String> translators;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String> languages;
    private Integer downloadCount;
    private Map<String, String> formats;

    public Book() {
    }

    public Book(BooksData booksData) {
        this.id = booksData.id();
        this.title = booksData.title();
        this.authors = booksData.authors();
        this.translators = booksData.translators();
        this.subjects = booksData.subjects();
        this.bookshelves = booksData.bookshelves();
        this.languages = booksData.languages();
        this.downloadCount = booksData.downloadCount();
        this.formats = booksData.formats();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", translators=" + translators +
                ", subjects=" + subjects +
                ", bookshelves=" + bookshelves +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                ", formats=" + formats +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getTranslators() {
        return translators;
    }

    public void setTranslators(List<String> translators) {
        this.translators = translators;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, String> formats) {
        this.formats = formats;
    }
}