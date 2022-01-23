package com.gcg.readingisgood.controller.v1.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class StatisticsResponse {

    private String month;
    private Integer orderCount;
    private Integer bookCount;
    private Double amount;

}
