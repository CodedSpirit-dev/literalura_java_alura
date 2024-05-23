package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.Book;
import com.aluracursos.literalura.model.Data;
import com.aluracursos.literalura.repository.BooksRepository;
import com.aluracursos.literalura.service.ApiConsumer;
import com.aluracursos.literalura.service.DataProcessor;

import java.util.Optional;
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
    private String bookName;

    public Main(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void showMenu () {
        var option = - 1;
        while ( option != 0 ) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    getBookDataFromApi();
                    break;
                case 2:
                    showStoredBooks();
                    break;
                case 3:
                    authorsListStored();
                    break;
                case 4:
                    getAuthorYear();
                    break;
                case 5:
                    findBooksByLanguages();
                    break;
            }
        }
    }

    private String userInput(){
        System.out.println("Enter the book name that you want to search for: ");
        bookName = scanner.nextLine();
        return bookName;
    }

    //Getting books from the API
    private Data getBookFromApi(String bookName) {
        var bookData = apiConsumer.obtainData(API_URL + bookName);
        return dataProcessor.obtainData(bookData, Data.class);
    }

    private Optional<Book> getBookData(Data bookData, String bookName) {
        var book = bookData.results().stream()
                .filter( b -> b.title().toLowerCase().contains(bookName.toLowerCase()))
                .map(b -> new Book(b.title(), b.languages(), b.downloads(), b.authors()))
                .findFirst();
        return book;
    }

    private void findBooksByLanguages() {
    }

    private void getAuthorYear() {
    }

    private void authorsListStored() {
    }

    private void showStoredBooks() {
    }

    private void getBookDataFromApi() {
    }
}
