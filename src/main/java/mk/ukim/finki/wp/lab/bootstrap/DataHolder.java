package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>(10);
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.add(new Book("Book1", "Genre1", 9.4));
        books.add(new Book("Book2", "Genre2", 5.5));
        books.add(new Book("Book3", "Genre2", 2.9));
        books.add(new Book("Book4", "Genre1", 8.1));
        books.add(new Book("Book5", "Genre3", 9.5));
        books.add(new Book("Book6", "Genre4", 9.4));
        books.add(new Book("Book7", "Genre3", 1.2));
        books.add(new Book("Book8", "Genre3", 3.4));
        books.add(new Book("Book9", "Genre2", 5.6));
        books.add(new Book("Book10", "Genre1", 7.8));
    }
}
