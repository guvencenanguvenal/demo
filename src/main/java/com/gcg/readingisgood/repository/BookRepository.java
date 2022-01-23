package com.gcg.readingisgood.repository;

import com.gcg.readingisgood.model.repository.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    @Override
    Optional<Book> findById(String id);

    @Override
    List<Book> findAll();

    Book findByName(String name);

    Book findByIsbn(String isbn);

    List<Book> findByIsbnIn(List<String> isbns);

}
