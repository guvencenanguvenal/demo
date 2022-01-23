package com.gcg.readingisgood.controller.v1.api;

import com.gcg.readingisgood.controller.v1.model.request.CreateOrderRequest;
import com.gcg.readingisgood.exception.EntityType;
import com.gcg.readingisgood.exception.ExceptionType;
import com.gcg.readingisgood.model.Response;
import com.gcg.readingisgood.model.dto.BookDTO;
import com.gcg.readingisgood.model.dto.CustomerDTO;
import com.gcg.readingisgood.model.dto.OrderDTO;
import com.gcg.readingisgood.service.BookService;
import com.gcg.readingisgood.service.CustomerService;
import com.gcg.readingisgood.service.OrderService;
import com.gcg.readingisgood.util.DateUtil;
import com.gcg.readingisgood.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Response saveOrder(@Valid @RequestBody CreateOrderRequest request) {

        CustomerDTO customerDTO = customerService.getCustomerByEmail(request.getCustomerEmail());

        if (customerDTO != null) {
            BookDTO bookDTO = bookService.getBookByIsbn(request.getIsbn());

            OrderDTO orderDTO = new OrderDTO()
                    .setOrderDate(DateUtil.today())
                    .setCustomerId(customerDTO.getId())
                    .setBook(bookDTO);

            return Response.ok().setPayload(orderService.createOrder(orderDTO));
        }

        return Response.notFound().setErrors("User not found!");

    }

    @GetMapping(params = "id")
    public Response getOrderById(@Valid @NotNull @RequestParam("id") String id) {
        return Response.ok().setPayload(orderService.getOrderById(id));
    }

    @GetMapping(params = {"from", "to"})
    public Response getOrderByIntervalDate(@Valid @NotNull @RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from, @Valid @NotNull @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to) {
        return Response.ok().setPayload(orderService.getOrdersByIntervalDate(from, to));
    }


}
