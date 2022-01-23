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
public class CreateCustomerRequest {

    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String password;

}
