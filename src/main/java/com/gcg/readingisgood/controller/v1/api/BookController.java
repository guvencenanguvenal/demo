package com.gcg.readingisgood.controller.v1.api;

import com.gcg.readingisgood.controller.v1.model.request.CreateBookRequest;
import com.gcg.readingisgood.controller.v1.model.request.UpdateBookStockRequest;
import com.gcg.readingisgood.model.Response;
import com.gcg.readingisgood.model.dto.BookDTO;
import com.gcg.readingisgood.service.BookService;
import com.gcg.readingisgood.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/v1/book")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MapperUtil mapperUtil;

    @GetMapping
    public Response getBookByIsbn(/*@ISBN*/ @NotEmpty @RequestParam("isbn") String isbn) {
        return Response.ok().setPayload(bookService.getBookByIsbn(isbn));
    }

    @PostMapping
    public Response saveBook(@Valid @RequestBody CreateBookRequest request) {
        return Response.ok().setPayload(bookService.addBook(mapperUtil.map(request, BookDTO.class)));
    }

    @PostMapping("/stock")
    public Response updateStock(@Valid @RequestBody UpdateBookStockRequest request) {
        return Response.ok().setPayload(bookService.updateStock(request.getIsbn(), request.getStock()));
    }

}
