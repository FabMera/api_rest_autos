package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comprar")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_factura")
    private Integer numberBill;

    @Column(name = "cliente_cedula")
    private String cardIdCustomer;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateBill;

    private Double total;

    @Column(name = "metodo_pago")
    private String paymentMethod;

    //No se debe insertar ni actualizar cliente_cedula en @ManyToOne (insertable ,updatable))
    //ya que es una llave foranea porque ya esta en la tabla y con join column se hace la relacion.
    @ManyToOne
    @JoinColumn(name = "cliente_cedula", insertable = false, updatable = false)
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "purchaseEntity", cascade = CascadeType.ALL)
    private List<CarPurchaseEntity> carsPurchaseDTO;


}
