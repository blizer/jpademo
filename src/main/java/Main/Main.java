package Main;

import config.JPAConfig;
import entity.BookDetailsEntity;
import entity.BookEntity;
import entity.CategoryEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.BookRepository;
import repository.CategoryRepository;

import java.time.LocalDate;
import java.util.List;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");
    static CategoryRepository categoryRepository = (CategoryRepository) context.getBean("categoryRepository");

    public static void main(String[] args) {
        createNewBookEntryWithNewCategory();
        createNewBookEntry();
        findByAuthor("Roger");
        findByNameAndAuthor("linux","Roger");
        findByNameOrAuthor("linux", "Roger");
        findByPriceLessThan(80);
        findByBookDetailsIsbn("ISIBF1219321");
        findByNameContaining("Nu");
    }

    public static void findByAuthor(String author){
        List<BookEntity> bookEntityList = bookRepository.findByAuthor(author);
        if (bookEntityList != null)
        {
            System.out.println("\nFind "+ bookEntityList.size() +" books which author = "+ author);
            for (BookEntity bookEntity: bookEntityList)
            {
                System.out.println(bookEntity.toString());
            }
        }
    }
    public static void findByNameAndAuthor (String name, String author){
        List<BookEntity> bookEntityList = bookRepository.findByNameAndAuthor(name, author);
        if (bookEntityList != null){
            System.out.println("\nFind "+ bookEntityList.size() + " books which name = "
                    + name + " and author = "+ author);
            for (BookEntity bookEntity: bookEntityList)
            {
                System.out.println(bookEntity.toString());
            }
        }
    }
    public static void findByNameOrAuthor (String name, String author){
        List<BookEntity> bookEntityList = bookRepository.findByNameOrAuthor(name, author);
        if (bookEntityList != null){
            System.out.println("\nFind "+ bookEntityList.size() + " books which name = "
                    + name + " or author = " + author);
            for (BookEntity bookEntity: bookEntityList)
            {
                System.out.println(bookEntity.toString());
            }
        }
    }
    public static void findByPriceLessThan (int price){
        List<BookEntity> bookEntityList = bookRepository.findByBookDetailsPriceLessThan(price);
        if (bookEntityList != null){
            System.out.println("\nFind "+ bookEntityList.size() + " books price lass than " + price);
            for (BookEntity bookEntity: bookEntityList)
            {
                System.out.println(bookEntity.toString());
            }
        }
    }
    public static void findByNameContaining (String name){
        List<BookEntity> bookEntityList = bookRepository.findByNameContaining(name);
        if (bookEntityList != null){
            System.out.println("\nFind "+ bookEntityList.size() +
                    " books which containing name = " + name);
            for (BookEntity bookEntity: bookEntityList)
            {
                System.out.println(bookEntity.toString());
            }
        }
    }
    public static void findAllUsingQuery (){
        List<BookEntity> bookEntityList = (List<BookEntity>) bookRepository.findAll();
        if (bookEntityList != null){
            System.out.println("\nFind "+ bookEntityList.size() + " books");
            for (BookEntity bookEntity: bookEntityList)
            {
                System.out.println(bookEntity.toString());
            }
        }
    }
    public static void findByBookDetailsIsbn (String isbn){
        BookEntity bookEntity = bookRepository.findByBookDetailsIsbn(isbn);
        if (bookEntity != null){
            System.out.println("\nFind book which isbn = "+ isbn);
            System.out.println(bookEntity.toString());
        }
    }
    public static void createNewBookEntry() {
        //Instance object Category with ID = 1
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);

        BookEntity bookEntity = createNewBook();
        //set book category
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }
    public static void createNewBookEntryWithNewCategory() {
        CategoryEntity categoryEntity = createNewCategory();
        categoryRepository.save(categoryEntity);

        BookEntity bookEntity = createNewBook();
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }
    private static CategoryEntity createNewCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("IT");
        categoryEntity.setDescrition("IT books");
        return categoryEntity;
    }
    private static BookEntity createNewBook() {
        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setIsbn("ISIBF1219323");
        bookDetailsEntity.setNumberOfPage(23);
        bookDetailsEntity.setPrice(65);
        bookDetailsEntity.setPublishDate(LocalDate.now());

        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Java A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setBookDetails(bookDetailsEntity);
        bookDetailsEntity.setBook(bookEntity);

        return bookEntity;
    }
    public static void findBookNameStartWithUsingQuery (String name){
        List<BookEntity> bookEntityList = bookRepository.getBookNameStartWith(name);
        if (bookEntityList != null){
            System.out.println("\n find "+ bookEntityList.size() + " books");
            for (BookEntity bookEntity: bookEntityList){
                System.out.println(bookEntity.toString());
            }
        }
    }
    public static void findBookPriceGreaterThanUsingQuery(int price){
        List<BookEntity> bookEntityList = bookRepository.getBookPriceGreaterThan(price);
        if (bookEntityList != null)
        {
            System.out.println("\nFind "+ bookEntityList.size() + " books");
            for (BookEntity bookEntity: bookEntityList)
            {
                System.out.println(bookEntity.toString());
            }
        }
    }
}