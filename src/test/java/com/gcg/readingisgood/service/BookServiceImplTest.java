package com.gcg.readingisgood.service;

import com.gcg.readingisgood.model.dto.BookDTO;
import com.gcg.readingisgood.model.repository.Book;
import com.gcg.readingisgood.repository.BookRepository;
import com.gcg.readingisgood.util.MapperUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class BookServiceImplTest {

    private final String ISBN = "123123";
    private final Integer STOCK = 2;

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MapperUtil mapperUtil;

    @Before
    public void before(){
        MockitoAnnotations.openMocks(bookService);
    }

    @Test
    public void whenCheckStock_withValidStockIsbn_shouldReturnTrue(){

        Book book = new Book()
                .setStock(1);

        Mockito.when(bookRepository.findByIsbn(ISBN)).thenReturn(book);

        Boolean response = bookService.checkStock(ISBN);

        Assert.assertEquals(true, response);


    }

    @Test
    public void whenCheckStock_withInvalidStockIsbn_shouldReturnFalse(){

        Book book = new Book()
                .setStock(0);

        Mockito.when(bookRepository.findByIsbn(ISBN)).thenReturn(book);

        Boolean response = bookService.checkStock(ISBN);

        Assert.assertEquals(false, response);


    }

    @Test
    public void whenUpdateStock_withValidInput_shouldReturnBookDTO() {
        Book book = new Book()
                .setStock(0);

        Book updatedBook = new Book()
                .setStock(STOCK);

        BookDTO bookDTO = new BookDTO()
                .setStock(updatedBook.getStock());

        Mockito.when(bookRepository.findByIsbn(ISBN)).thenReturn(updatedBook);
        Mockito.when(bookRepository.save(updatedBook)).thenReturn(updatedBook);
        Mockito.when(mapperUtil.map(updatedBook, BookDTO.class)).thenReturn(bookDTO);

        BookDTO response = bookService.updateStock(ISBN, STOCK);

        Assert.assertEquals(bookDTO, response);
    }

    @Test
    public void whenGetBookByIsbn_withValidIsbn_shouldReturnBookDTO(){
        Book book = new Book();
        BookDTO bookDTO = new BookDTO();

        Mockito.when(bookRepository.findByIsbn(ISBN)).thenReturn(book);
        Mockito.when(mapperUtil.map(book, BookDTO.class)).thenReturn(bookDTO);

        BookDTO response = bookService.getBookByIsbn(ISBN);

        Assert.assertEquals(bookDTO, response);
    }

}