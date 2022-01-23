package com.gcg.readingisgood.controller.v1.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CreateBookRequest {

    @NotNull
    @NotEmpty
    //@ISBN
    private String isbn;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String writer;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;

}
