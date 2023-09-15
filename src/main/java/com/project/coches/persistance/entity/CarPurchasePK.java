package com.project.coches.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarPurchasePK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "compras_numero_facturas")
    private Integer purchaseNumberBill;
    @Column(name = "autos_codigo")
    private Integer codeCar;
}
