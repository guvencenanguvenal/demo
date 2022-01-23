package com.gcg.readingisgood.controller.v1.api;

import com.gcg.readingisgood.controller.v1.model.response.StatisticsResponse;
import com.gcg.readingisgood.model.Response;
import com.gcg.readingisgood.model.dto.BookDTO;
import com.gcg.readingisgood.model.dto.OrderDTO;
import com.gcg.readingisgood.service.OrderService;
import com.gcg.readingisgood.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/statistics")
@Validated
public class StatisticsController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public Response getOrderMonthly(@Min(1) @Max(12) @RequestParam("month") Integer month) throws ParseException {

        List<OrderDTO> orderList =
                orderService.getOrdersByIntervalDate(DateUtil.getMonthFirstDay(month.toString()),
                        DateUtil.getMonthLastDay(month.toString()));
        List<BookDTO> bookList = orderList.stream().map(element -> element.getBook()).collect(Collectors.toList());
        List<Double> prices = orderList.stream().map(element -> element.getBook().getPrice()).collect(Collectors.toList());

        return Response.ok().setPayload(new StatisticsResponse()
                .setMonth(month.toString())
                .setOrderCount(orderList.size())
                .setBookCount(bookList.size())
                .setAmount(prices.stream().reduce(0d, (subTotal, element) -> subTotal + element))
        );
    }




}
