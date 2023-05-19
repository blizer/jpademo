package Main;

import config.JPAConfig;
import entity.BookEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.BookRepository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");

    public static void main (String[] args){
        //createNewBook();
        //readBook(1);
        //updateBook();
        deleteBook();
    }
    private static void createNewBook(){
        //prepare data
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Linux shell programming");
        bookEntity.setAuthor("Bruno");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("ISIBF12232323");
        bookEntity.setNumberOfPage(135);
        bookEntity.setPrice(12.6);
        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));

        BookEntity result = bookRepository.save(bookEntity);

        if (result != null) {
            System.out.println("A new book saved successfully, book ID = "+ bookEntity.getId());
        }
    }

    private static void readBook(){
        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
        System.out.println("Found "+ bookList.size() + " book(s) in the table book");
        System.out.println("They are: ");
        for(BookEntity book: bookList) {
            System.out.println(book.toString());
        }
    }
    private static void readBook(int bookID){
        Optional<BookEntity> bookEntity = bookRepository.findById(bookID);
        if(bookEntity != null)
        {
            System.out.println("Found a book with book ID = " + bookID);
            System.out.println(bookEntity.toString());
        }
        else
        {
            System.out.println("Not found any book with book ID = " + bookID);
        }
    }
    private static void updateBook () {
        BookEntity bookEntity = bookRepository.findById(1);
        System.out.println("Book data before updating");
        System.out.println(bookEntity.toString());

        bookEntity.setAuthor("Jame");
        bookEntity.setNumberOfPage(199);
        bookEntity.setPrice(201);
        bookRepository.save(bookEntity);

        System.out.println("book data after updating");
        System.out.println(bookEntity.toString());
    }
    private static void deleteBook() {
        BookEntity bookEntity = bookRepository.findById(2);
        if(bookEntity != null) {
            bookRepository.delete(bookEntity);
        }
        else
        {
            System.out.println("khong ton tai book co ID nay");
        }
    }
}
