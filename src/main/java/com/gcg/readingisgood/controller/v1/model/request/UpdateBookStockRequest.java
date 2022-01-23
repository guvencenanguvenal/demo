package com.gcg.readingisgood.controller.v1.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UpdateBookStockRequest {

    @NotNull
    private String isbn;

    @NotNull
    private Integer stock;

}
