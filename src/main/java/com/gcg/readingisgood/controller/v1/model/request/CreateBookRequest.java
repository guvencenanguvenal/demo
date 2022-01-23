package com.gcg.readingisgood.controller.v1.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CreateBookRequest {

    @NotNull
    private String isbn;
    @NotNull
    private String name;
    @NotNull
    private String writer;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;

}
