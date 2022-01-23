package com.gcg.readingisgood.service;

import com.gcg.readingisgood.model.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers(Integer page, Integer size);
    
    CustomerDTO getCustomerByEmail(String email);

    CustomerDTO getCustomerById(String id);

    CustomerDTO addCustomer(CustomerDTO customer);

}
