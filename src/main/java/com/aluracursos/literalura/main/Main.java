package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.Author;
import com.aluracursos.literalura.model.Book;
import com.aluracursos.literalura.model.Data;
import com.aluracursos.literalura.repository.BooksRepository;
import com.aluracursos.literalura.service.ApiConsumer;
import com.aluracursos.literalura.service.DataProcessor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final String API_URL = "https://gutendex.com/books/";
    private static final Scanner scanner = new Scanner(System.in);
    private static final ApiConsumer apiConsumer = new ApiConsumer();
    private static final DataProcessor dataProcessor = new DataProcessor();
    private final BooksRepository booksRepository;
    private String bookSelected;

    public Main(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void showMenu() {
        int option;
        do {
            System.out.println("""
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> getBookData();
                case 2 -> showStoredBooks();
                case 3 -> authorsListStored();
                case 4 -> getAuthorYear();
                case 5 -> findBooksByLanguages();
            }
        } while (option != 0);
    }

    private String userInput() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        bookSelected = scanner.nextLine();
        return bookSelected;
    }

    private Data getBookFromApi(String bookName) {
        var json = apiConsumer.obtainData(API_URL + "?search=" + bookName.replace(" ", "+"));
        var data = dataProcessor.obtainData(json, Data.class);
        return data;
    }

    private Optional<Book> getBookData(Data bookData, String bookName) {
        Optional<Book> books = bookData.results().stream()
                .filter(b -> b.title().toLowerCase().contains(bookName.toLowerCase()))
                .map(b -> new Book(b.title(), b.languages(), b.downloads(), b.authors()))
                .findFirst();

        return books;
    }

    private Optional<Book> getBookData() {
        String bookName = userInput();
        Data bookData = getBookFromApi(bookName);
        Optional<Book> book = getBookData(bookData, bookName);
        //Print the book if it is present
        book.ifPresentOrElse(
                b -> {
                    System.out.println("Found book: " + b.getTitle());
                    booksRepository.save(b);
                },
                () -> System.out.println("Book not found.")
        );
        return book;
    }

    private void showStoredBooks() {
        List<Book> books = booksRepository.findAllBooks();
        // Print the books
        books.stream()
                .map(b -> b.getTitle())
                //Print only the title
                .forEach(System.out::println);


    }

    private void authorsListStored() {
        List<Author> authors = booksRepository.findAllAuthors();
        authors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .forEach(a -> System.out.printf("Author: %s Born: %d Died: %d%n", a.getName(), a.getBirthYear(), a.getDeathYear()));
    }

    private void getAuthorYear() {
        System.out.println("Ingrese el año: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        List<Author> authors = booksRepository.findAllAuthorsLivingInYear(year);
        authors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .forEach(a -> System.out.printf("Author: %s Born: %d Died: %d%n", a.getName(), a.getBirthYear(), a.getDeathYear()));
    }

    private void findBooksByLanguages() {
        System.out.println("Ingrese el idioma: ");
        String language = scanner.nextLine();
        List<Book> books = booksRepository.findAllBooksByLanguages(language);
        books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

}