package com.gcg.readingisgood.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
public class OrderDTO {

    private String id;
    private BookDTO book;
    private Date orderDate;
    private String customerId;

}
