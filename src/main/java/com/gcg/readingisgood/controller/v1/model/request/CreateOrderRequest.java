package com.gcg.readingisgood.controller.v1.model.request;

import com.gcg.readingisgood.model.repository.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CreateOrderRequest {

    @NotNull
    private String isbn;

    @NotNull
    private String customerEmail;

}
