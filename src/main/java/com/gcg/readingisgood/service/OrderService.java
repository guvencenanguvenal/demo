package com.gcg.readingisgood.service;

import com.gcg.readingisgood.model.dto.OrderDTO;

import java.util.Date;
import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders();

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(String id);

    List<OrderDTO> getCustomerOrders(String customerId);

    List<OrderDTO> getOrdersByIntervalDate(Date from, Date to);

}
