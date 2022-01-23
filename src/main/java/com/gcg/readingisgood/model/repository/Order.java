package com.gcg.readingisgood.model.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "order")
public class Order {

    @Id
    private String id;

    @DBRef
    private Book book;

    private Date orderDate;
    private String customerId;

}
