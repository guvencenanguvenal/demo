package com.gcg.readingisgood.controller.v1.api;

import com.gcg.readingisgood.controller.v1.model.request.CreateCustomerRequest;
import com.gcg.readingisgood.exception.EntityType;
import com.gcg.readingisgood.exception.ExceptionType;
import com.gcg.readingisgood.model.Response;
import com.gcg.readingisgood.model.dto.CustomerDTO;
import com.gcg.readingisgood.service.CustomerService;
import com.gcg.readingisgood.service.OrderService;
import com.gcg.readingisgood.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public Response getAllUsers(@RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "3") Integer size) {
        return Response.ok().setPayload(customerService.getAllCustomers(page, size));
    }

    @GetMapping
    public Response getUser(@Valid @NotNull @RequestParam(name = "email") String email){
        return Response.ok().setPayload(customerService.getCustomerByEmail(email));
    }

    @PostMapping
    public Response saveUser(@Valid @RequestBody CreateCustomerRequest request) {
        return Response.ok().setPayload(customerService.addCustomer(modelMapper.map(request, CustomerDTO.class)));
    }

    @GetMapping("/order")
    public Response getCustomerOrders(@Valid @NotNull @RequestParam(name = "email") String email) {

        CustomerDTO customer = customerService.getCustomerByEmail(email);

        if (customer != null){
            return Response.ok().setPayload(orderService.getCustomerOrders(customer.getId()));
        }

        throw ExceptionUtil.throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email);
    }

}
