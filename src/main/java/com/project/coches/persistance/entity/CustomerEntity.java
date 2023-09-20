package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @Column(unique = true)
    @NotBlank(message = "El email no puede estar vacio")
    private String email;
    @Column(name = "numero_telefono")
    private Double numberCellphone;
    @Column(name = "activo")
    private Integer active;
    @Column(name = "contrasena")
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;
    private String rol;
    @OneToMany(mappedBy = "customerEntity")
    private List<PurchaseEntity> purchaseEntity;
}
