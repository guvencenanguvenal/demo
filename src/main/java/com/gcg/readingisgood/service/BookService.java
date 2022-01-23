package com.gcg.readingisgood.service;

import com.gcg.readingisgood.model.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO getBookByIsbn(String isbn);

    List<BookDTO> getAllBooksByIsbnList(List<String> isbnList);

    BookDTO addBook(BookDTO bookDTO);

    BookDTO updateStock(String isbn, Integer stock);

    Boolean checkStock(String isbn);

}
