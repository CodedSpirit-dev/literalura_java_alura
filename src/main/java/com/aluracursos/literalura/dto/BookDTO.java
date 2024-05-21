package com.aluracursos.literalura.dto;

import java.util.List;
import java.util.Map;

public record BookDTO(
        Integer id,
        String title,
        List<String> authors,
        List<String> translators,
        List<String> subjects,
        List<String> bookshelves,
        List<String> languages,
        Integer downloadCount,
        Map<String, String> formats
) {
}
