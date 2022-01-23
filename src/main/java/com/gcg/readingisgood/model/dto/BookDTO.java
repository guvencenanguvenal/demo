package com.gcg.readingisgood.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class BookDTO {

    private String id;
    private String isbn;
    private String name;
    private String writer;
    private Double price;
    private Integer stock;

}
