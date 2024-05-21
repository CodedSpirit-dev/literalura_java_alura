package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData(
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<String> authors,
        @JsonAlias("translators") List<String> translators,
        @JsonAlias("subjects") List<String> subjects,
        @JsonAlias("bookshelves") List<String> bookshelves,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Integer downloadCount,
        Map<String, String> formats
) {
}