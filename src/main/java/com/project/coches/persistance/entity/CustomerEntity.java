package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "nombre_completo")
    private String fullName;
    private String email;
    @Column(name = "numero_telefono")
    private Double numberCellphone;
    @Column(name = "activo")
    private Integer active;
    @Column(name = "contrasena")
    private String password;
    private String rol;
    @OneToMany(mappedBy = "customerEntity")
    private List<PurchaseEntity> purchaseEntity;
}
