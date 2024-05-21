package com.aluracursos.literalura.main;

import com.aluracursos.literalura.repository.BooksRepository;
import com.aluracursos.literalura.service.ApiConsumer;
import com.aluracursos.literalura.service.DataProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //Constant for the API URL
    private static final String API_URL = "https://gutendex.com/books/";
    //Scanner for reading user input
    private static final Scanner scanner = new Scanner(System.in);
    //Service for consuming the API
    private static final ApiConsumer apiConsumer = new ApiConsumer();
    // Service for processing the data
    private static final DataProcessor dataProcessor = new DataProcessor();
    //Add repository for data persistence
    private final BooksRepository booksRepository;

    public Main(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
}
