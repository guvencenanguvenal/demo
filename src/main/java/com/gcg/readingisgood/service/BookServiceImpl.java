package com.gcg.readingisgood.service;

import com.gcg.readingisgood.exception.EntityType;
import com.gcg.readingisgood.exception.ExceptionType;
import com.gcg.readingisgood.model.dto.BookDTO;
import com.gcg.readingisgood.model.repository.Book;
import com.gcg.readingisgood.repository.BookRepository;
import com.gcg.readingisgood.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));

        if (book.isPresent()) {
            return modelMapper.map(book.get(), BookDTO.class);
        }

        throw  ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.ENTITY_NOT_FOUND, isbn);
    }

    @Override
    public List<BookDTO> getAllBooksByIsbnList(List<String> isbnList) {
        Optional<List<Book>> books = Optional.ofNullable(bookRepository.findByIsbnIn(isbnList));

        if (books.isPresent()) {
            return books.get().stream().map(element -> modelMapper.map(element, BookDTO.class)).collect(Collectors.toList());
        }

        throw  ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.ENTITY_NOT_FOUND, "");
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {

        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(bookDTO.getIsbn()));

        if (!book.isPresent()) {
             return modelMapper.map(
                     bookRepository.save(new Book()
                             .setName(bookDTO.getName())
                             .setIsbn(bookDTO.getIsbn())
                             .setWriter(bookDTO.getWriter())
                             .setPrice(bookDTO.getPrice())
                             .setStock(bookDTO.getStock())),
                     BookDTO.class);
        }

        throw ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.DUPLICATE_ENTITY, bookDTO.getName());

    }

    @Override
    @Transactional
    public BookDTO updateStock(String isbn, Integer stock) {

        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));

        if (book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setStock(stock);

            return modelMapper.map(bookRepository.save(updatedBook), BookDTO.class);
        }

        throw  ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.ENTITY_NOT_FOUND, isbn);
    }

    @Override
    @Transactional
    public Boolean checkStock(String isbn) {

        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));

        if (book.isPresent()) {
            return book.get().getStock() > 0;
        }

        throw  ExceptionUtil.throwException(EntityType.BOOK, ExceptionType.ENTITY_NOT_FOUND, isbn);

    }


}
