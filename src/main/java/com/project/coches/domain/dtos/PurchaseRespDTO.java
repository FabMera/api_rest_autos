package com.project.coches.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
/*dto de la consulta de una compra*/

@Getter
@Setter

public class PurchaseRespDTO {
    private Integer numberBill;

    private String cardIdCustomer;

    private LocalDateTime dateBill;

    private Double total;

    private String paymentMethod;

    private List<CarPurchaseRespDTO> carsPurchaseRespDTO;
}
