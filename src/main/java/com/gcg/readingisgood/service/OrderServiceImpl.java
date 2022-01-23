package com.gcg.readingisgood.service;


import com.gcg.readingisgood.exception.EntityType;
import com.gcg.readingisgood.exception.ExceptionType;
import com.gcg.readingisgood.model.dto.OrderDTO;
import com.gcg.readingisgood.model.repository.Order;
import com.gcg.readingisgood.repository.OrderRepository;
import com.gcg.readingisgood.util.ExceptionUtil;
import com.gcg.readingisgood.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(element -> mapperUtil.map(element, OrderDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {

        if (bookService.checkStock(orderDTO.getBook().getIsbn()))
            bookService.updateStock(orderDTO.getBook().getIsbn(), orderDTO.getBook().getStock() - 1);
        else {
            log.error(orderDTO.getBook().getIsbn() + " - book stock not found!");
            throw ExceptionUtil.throwException(EntityType.ORDER, ExceptionType.ENTITY_NOT_FOUND, "Book stock not found!");
        }

        return mapperUtil.map(orderRepository.save(mapperUtil.map(orderDTO, Order.class)), OrderDTO.class);
    }

    @Override
    public OrderDTO getOrderById(String id) {

        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            return mapperUtil.map(order.get(), OrderDTO.class);
        }

        log.error(id + " - order not found!");

        throw ExceptionUtil.throwException(EntityType.ORDER, ExceptionType.ENTITY_NOT_FOUND, id);

    }

    @Override
    public List<OrderDTO> getCustomerOrders(String customerId) {

        List<OrderDTO> response = new ArrayList<>();

        Optional<List<Order>> orders = Optional.ofNullable(orderRepository.findByCustomerId(customerId));

        if (orders.isPresent() && orders.get().size() != 0) {
            for (Order order : orders.get()) {
                response.add(mapperUtil.map(order, OrderDTO.class));
            }

            return response;
        }

        log.error(customerId + " - customer not found!");

        throw ExceptionUtil.throwException(EntityType.ORDER, ExceptionType.ENTITY_NOT_FOUND, customerId);
    }

    @Override
    public List<OrderDTO> getOrdersByIntervalDate(Date from, Date to) {
        return orderRepository.findByOrderDateBetween(from, to).stream().map(element -> mapperUtil.map(element, OrderDTO.class)).collect(Collectors.toList());
    }
}
