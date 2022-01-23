package com.gcg.readingisgood.model.dto;

import com.gcg.readingisgood.model.repository.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CustomerDTO {

    private String id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private List<Order> orders;

}
