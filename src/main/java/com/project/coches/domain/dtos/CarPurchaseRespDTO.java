package com.project.coches.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarPurchaseRespDTO {
    private String referenceCar;
    private Integer quantity;

    private  Integer total;
}
