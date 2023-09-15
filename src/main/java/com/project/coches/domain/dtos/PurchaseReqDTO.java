package com.project.coches.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PurchaseReqDTO {

    private Integer numberBill;
    private String cardIdCustomer;
    private LocalDateTime dateBill;
    private Double total;
    private String paymentMethod;
    private List<CarPurchaseReqDTO> carsPurchaseDTO;

}
