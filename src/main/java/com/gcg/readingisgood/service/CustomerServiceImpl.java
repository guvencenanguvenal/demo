package com.gcg.readingisgood.service;

import com.gcg.readingisgood.exception.EntityType;
import com.gcg.readingisgood.exception.ExceptionType;
import com.gcg.readingisgood.model.dto.CustomerDTO;
import com.gcg.readingisgood.model.repository.Customer;
import com.gcg.readingisgood.repository.CustomerRepository;
import com.gcg.readingisgood.util.ExceptionUtil;
import com.gcg.readingisgood.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<CustomerDTO> getAllCustomers(Integer page, Integer size) {

        Page<Customer> customerPage = customerRepository.findAll(PageRequest.of(page, size));

        return customerPage.getContent().stream().map(element -> mapperUtil.map(element, CustomerDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {

        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByEmail(email));

        if (customer.isPresent()){
            return mapperUtil.map(customer.get(), CustomerDTO.class);
        }

        log.error(email + " - customer not found!");

        throw ExceptionUtil.throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email);
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()){
            return mapperUtil.map(customer.get(), CustomerDTO.class);
        }

        log.error(id + " - customer not found!");

        throw ExceptionUtil.throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, id);
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {

        Customer customer = customerRepository.findByEmail(customerDTO.getEmail());

        if (customer == null) {
            customer = new Customer()
                    .setEmail(customerDTO.getEmail())
                    .setName(customerDTO.getName())
                    .setSurname(customerDTO.getSurname())
                    .setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));

            log.info(customer.toString() + " - new customer! ");

            return mapperUtil.map(customerRepository.save(customer), CustomerDTO.class);
        }

        log.error(customerDTO.toString() + " - customer duplicate!");

        throw ExceptionUtil.throwException(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, customerDTO.getEmail());

    }

}
