package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "autos_compras")
public class CarPurchaseEntity {


    @EmbeddedId
    private CarPurchasePK id;
    @Column(name = "cantidad")
    private Integer quantity;

    private Integer totalCars;

    @ManyToOne
    @JoinColumn(name = "compras_numero_facturas", insertable = false, updatable = false)
    private PurchaseEntity purchaseEntity;

    @ManyToOne
    @JoinColumn(name ="autos_codigo",insertable = false, updatable = false )
    private CarEntity carEntity;
}
