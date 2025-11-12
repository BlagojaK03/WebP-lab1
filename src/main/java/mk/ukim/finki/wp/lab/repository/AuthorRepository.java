package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    public List<Author> authors;

    public AuthorRepository() {
        authors = new ArrayList<>(3);

        authors.add(new Author(1L, "Author", "Lastname", "Country1", "Biograpany"));
        authors.add(new Author(2L, "John", "Author", "Country2", "Boaigraphy"));
        authors.add(new Author(3L, "Jerma", "985", "Country1", "MG2023"));
    }

    public List<Author> findAll() {
        return authors;
    }
}
