package com.gcg.readingisgood.service;

import com.gcg.readingisgood.model.dto.CustomerDTO;
import com.gcg.readingisgood.model.repository.Customer;
import com.gcg.readingisgood.repository.CustomerRepository;
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

import java.util.Optional;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class CustomerServiceImplTest {

    private final String INPUT_EMAIL = "guvencenan@yahoo.com";
    private final String INPUT_ID = "61ec3d4e5e93670c2a96f562";

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void before(){
        MockitoAnnotations.openMocks(customerService);
    }

    @Test
    public void whenGetCustomerByEmail_withValidEmail_shouldReturnCustomerDTO() {
        Customer customer = new Customer()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123")
                .setId("61ec3d4e5e93670c2a96f562")
                .setOrders(null);

        CustomerDTO customerDTO = new CustomerDTO()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123")
                .setId("61ec3d4e5e93670c2a96f562")
                .setOrders(null);

        Mockito.when(customerRepository.findByEmail(INPUT_EMAIL)).thenReturn(customer);
        Mockito.when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);

        CustomerDTO response = customerService.getCustomerByEmail(INPUT_EMAIL);
        Assert.assertEquals(customerDTO, response);

    }

    @Test
    public void whenGetCustomerById_withValidId_shouldReturnCustomerDTO(){
        Customer customer = new Customer()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123")
                .setId("61ec3d4e5e93670c2a96f562")
                .setOrders(null);

        CustomerDTO customerDTO = new CustomerDTO()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123")
                .setId("61ec3d4e5e93670c2a96f562")
                .setOrders(null);

        Mockito.when(customerRepository.findById(INPUT_ID)).thenReturn(Optional.of(customer));
        Mockito.when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);

        CustomerDTO response = customerService.getCustomerById(INPUT_ID);
        Assert.assertEquals(customerDTO, response);
    }

    @Test
    public void whenAddCustomer_withValidInput_shouldReturnCustomerDTO(){
        Customer savedCustomer = new Customer()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123");

        CustomerDTO inputCustomerDTO = new CustomerDTO()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123")
                .setId("61ec3d4e5e93670c2a96f562")
                .setOrders(null);

        Customer returnedCustomer = new Customer()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123")
                .setId("61ec3d4e5e93670c2a96f562")
                .setOrders(null);

        CustomerDTO returnedCustomerDTO = new CustomerDTO()
                .setEmail("guvencenan@yahoo.com")
                .setName("Guven")
                .setSurname("Guvenal")
                .setPassword("123")
                .setId("61ec3d4e5e93670c2a96f562")
                .setOrders(null);

        Mockito.when(customerRepository.findByEmail(inputCustomerDTO.getEmail())).thenReturn(null);
        Mockito.when(customerRepository.save(savedCustomer)).thenReturn(returnedCustomer);
        Mockito.when(modelMapper.map(returnedCustomer, CustomerDTO.class)).thenReturn(returnedCustomerDTO);

        CustomerDTO response = customerService.addCustomer(inputCustomerDTO);
        Assert.assertEquals(returnedCustomerDTO, response);
    }

}