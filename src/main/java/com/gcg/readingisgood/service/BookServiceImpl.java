package com.gcg.readingisgood.service;

import com.gcg.readingisgood.exception.EntityType;
import com.gcg.readingisgood.exception.ExceptionType;
import com.gcg.readingisgood.model.dto.BookDTO;
import com.gcg.readingisgood.model.repository.Book;
import com.gcg.readingisgood.repository.BookRepository;
import com.gcg.readingisgood.util.ExceptionUtil;
import com.gcg.readingisgood.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));

        if (book.isPresent()) {
            return mapperUtil.map(book.get(), BookDTO.class);
        }

        log.error(isbn + " - book not found!");

        throw  ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.ENTITY_NOT_FOUND, isbn);
    }

    @Override
    public List<BookDTO> getAllBooksByIsbnList(List<String> isbnList) {
        Optional<List<Book>> books = Optional.ofNullable(bookRepository.findByIsbnIn(isbnList));

        if (books.isPresent()) {
            return books.get().stream().map(element -> mapperUtil.map(element, BookDTO.class)).collect(Collectors.toList());
        }

        log.error(isbnList.stream().map(Object::toString).collect(Collectors.joining("-")) + " - books not found!");

        throw  ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.ENTITY_NOT_FOUND, isbnList.stream().map(Object::toString).collect(Collectors.joining("-")));
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {

        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(bookDTO.getIsbn()));

        if (!book.isPresent()) {

            Book insertedBook = new Book()
                    .setName(bookDTO.getName())
                    .setIsbn(bookDTO.getIsbn())
                    .setWriter(bookDTO.getWriter())
                    .setPrice(bookDTO.getPrice())
                    .setStock(bookDTO.getStock());

            log.info(insertedBook.toString() + " - book added");

             return mapperUtil.map(bookRepository.save(insertedBook),
                     BookDTO.class);
        }

        log.error(bookDTO.toString() + " - book duplicate!");

        throw ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.DUPLICATE_ENTITY, bookDTO.getName());

    }

    @Override
    @Transactional
    public BookDTO updateStock(String isbn, Integer stock) {

        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));

        if (book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setStock(stock);

            log.info(updatedBook.toString() + " - book updated");

            return mapperUtil.map(bookRepository.save(updatedBook), BookDTO.class);
        }

        log.error(isbn + " - book not found!");

        throw  ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.ENTITY_NOT_FOUND, isbn);
    }

    @Override
    @Transactional
    public Boolean checkStock(String isbn) {

        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));

        if (book.isPresent()) {
            return book.get().getStock() > 0;
        }

        log.error(isbn + " - book not found!");

        throw  ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.ENTITY_NOT_FOUND, isbn);

    }


}
