package com.gcg.readingisgood.controller.v1.api;

import com.gcg.readingisgood.controller.v1.model.request.CreateOrderRequest;
import com.gcg.readingisgood.model.Response;
import com.gcg.readingisgood.model.dto.BookDTO;
import com.gcg.readingisgood.model.dto.CustomerDTO;
import com.gcg.readingisgood.model.dto.OrderDTO;
import com.gcg.readingisgood.model.repository.Book;
import com.gcg.readingisgood.repository.OrderRepository;
import com.gcg.readingisgood.service.BookService;
import com.gcg.readingisgood.service.CustomerService;
import com.gcg.readingisgood.service.OrderService;
import com.gcg.readingisgood.service.OrderServiceImpl;
import com.gcg.readingisgood.util.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class OrderControllerTest {

    private final String INPUT_ID = "123";
    private final String INPUT_EMAIL = "guvencenan@yahoo.com";
    private final String ISBN = "123123";

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Mock
    private CustomerService customerService;

    @Mock
    private BookService bookService;

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void before(){
        MockitoAnnotations.openMocks(orderController);
    }

    @Test
    public void whenSaveOrder_withValidOrder_shouldReturnHttpOk(){

        CreateOrderRequest request = new CreateOrderRequest()
                .setCustomerEmail(INPUT_EMAIL)
                .setIsbn(ISBN);

        CustomerDTO customerDTO = new CustomerDTO()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123")
                .setId("61ec3d4e5e93670c2a96f562")
                .setOrders(null);

        BookDTO bookDTO = new BookDTO()
                .setStock(1);

        OrderDTO orderDTO = new OrderDTO()
                .setOrderDate(DateUtil.today())
                .setCustomerId(customerDTO.getId())
                .setBook(bookDTO);

        OrderDTO orderDTOReturned = new OrderDTO()
                .setOrderDate(DateUtil.today())
                .setCustomerId(customerDTO.getId())
                .setBook(bookDTO);

        Mockito.when(customerService.getCustomerByEmail(INPUT_EMAIL)).thenReturn(customerDTO);
        Mockito.when(bookService.getBookByIsbn(ISBN)).thenReturn(bookDTO);
        Mockito.when(orderService.createOrder(orderDTO)).thenReturn(orderDTOReturned);

        Response response = orderController.saveOrder(request);

        Assert.assertEquals(response.getStatus(), Response.Status.OK);

    }

    @Test
    public void whenSaveOrder_withNotFoundCustomer_shouldReturnHttpNotFound(){

        CreateOrderRequest request = new CreateOrderRequest()
                .setCustomerEmail(INPUT_EMAIL)
                .setIsbn(ISBN);

        Mockito.when(customerService.getCustomerByEmail(INPUT_EMAIL)).thenReturn(null);

        Response response = orderController.saveOrder(request);

        Assert.assertEquals(response.getStatus(), Response.Status.NOT_FOUND);

    }
}