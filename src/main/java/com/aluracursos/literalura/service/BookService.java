package com.aluracursos.literalura.service;

import com.aluracursos.literalura.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    //Add repository for data persistence
    @Autowired
    private final BooksRepository booksRepository;

    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
}
