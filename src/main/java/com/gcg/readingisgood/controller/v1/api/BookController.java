package com.gcg.readingisgood.controller.v1.api;

import com.gcg.readingisgood.controller.v1.model.request.CreateBookRequest;
import com.gcg.readingisgood.controller.v1.model.request.UpdateBookStockRequest;
import com.gcg.readingisgood.model.Response;
import com.gcg.readingisgood.model.dto.BookDTO;
import com.gcg.readingisgood.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Response getBookByIsbn(@Valid @NotNull @RequestParam("isbn") String isbn) {
        return Response.ok().setPayload(bookService.getBookByIsbn(isbn));
    }

    @PostMapping
    public Response saveBook(@Valid @RequestBody CreateBookRequest request) {
        return Response.ok().setPayload(bookService.addBook(modelMapper.map(request, BookDTO.class)));
    }

    @PostMapping("/stock")
    public Response updateStock(@Valid @RequestBody UpdateBookStockRequest request) {
        return Response.ok().setPayload(bookService.updateStock(request.getIsbn(), request.getStock()));
    }

}
