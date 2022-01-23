package com.gcg.readingisgood.service;

import com.gcg.readingisgood.model.dto.OrderDTO;
import com.gcg.readingisgood.model.repository.Order;
import com.gcg.readingisgood.repository.OrderRepository;
import com.gcg.readingisgood.util.MapperUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class OrderServiceImplTest {

    private final String INPUT_ID = "123";

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MapperUtil mapperUtil;

    @Before
    public void before(){
        MockitoAnnotations.openMocks(orderService);
    }

    @Test
    public void whenGetAllOrders(){

        List<Order> orderList = new ArrayList<>();

        Order order = new Order();
        OrderDTO orderDTO = new OrderDTO();

        Mockito.when(orderRepository.findAll()).thenReturn(orderList);
        Mockito.when(mapperUtil.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> response =  orderService.getAllOrders();

        Assert.assertEquals(response, new ArrayList<>());

    }

    @Test
    public void whenGetOrderById_withValidId_shouldReturnOrderDTO(){

        Order order = new Order();
        OrderDTO orderDTO = new OrderDTO();

        Mockito.when(orderRepository.findById(INPUT_ID)).thenReturn(Optional.of(order));
        Mockito.when(mapperUtil.map(order, OrderDTO.class)).thenReturn(orderDTO);

        OrderDTO response = orderService.getOrderById(INPUT_ID);

        Assert.assertEquals(response, orderDTO);

    }


}