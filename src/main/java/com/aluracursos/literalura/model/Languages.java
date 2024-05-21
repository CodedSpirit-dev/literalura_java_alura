package com.aluracursos.literalura.model;

public enum Languages {
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    ITALIAN("it"),
    PORTUGUESE("pt");

    private String languagesAlura;

    Languages ( String languagesAlura ) {
        this.languagesAlura = languagesAlura;
    }

    public static Languages fromString(String text) {
        for (Languages categoria : Languages.values())
            if ( categoria.languagesAlura.equalsIgnoreCase(text) ) {
                return categoria;
            }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

}