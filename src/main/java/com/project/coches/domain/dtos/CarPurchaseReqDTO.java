package com.project.coches.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarPurchaseReqDTO {
    private Integer purchaseNumberBill;
    private Integer codeCar;
    private Integer quantity;
    private Integer totalCars;
}
