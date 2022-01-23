package com.gcg.readingisgood.controller.v1.api;

import com.gcg.readingisgood.model.Response;
import com.gcg.readingisgood.model.dto.CustomerDTO;
import com.gcg.readingisgood.model.dto.OrderDTO;
import com.gcg.readingisgood.model.repository.Customer;
import com.gcg.readingisgood.repository.CustomerRepository;
import com.gcg.readingisgood.service.CustomerService;
import com.gcg.readingisgood.service.CustomerServiceImpl;
import com.gcg.readingisgood.service.OrderService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class CustomerControllerTest {

    private final String INPUT_EMAIL = "guvencenan@yahoo.com";

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @Mock
    OrderService orderService;

    @Before
    public void before(){
        MockitoAnnotations.openMocks(customerController);
    }

    @Test
    public void whenGetCustomerOrder_withValidCustomer_shouldReturnHttpOk() {

        CustomerDTO customerDTO = new CustomerDTO()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123")
                .setId("61ec3d4e5e93670c2a96f562")
                .setOrders(null);

        List<OrderDTO> orderDTOList = new ArrayList<>();

        Mockito.when(customerService.getCustomerByEmail(INPUT_EMAIL)).thenReturn(customerDTO);
        Mockito.when(orderService.getCustomerOrders(customerDTO.getId())).thenReturn(orderDTOList);

        Response response = customerController.getCustomerOrders(INPUT_EMAIL);

        Assert.assertEquals(response.getPayload(), orderDTOList);
        Assert.assertEquals(response.getStatus(), Response.Status.OK);
    }

}