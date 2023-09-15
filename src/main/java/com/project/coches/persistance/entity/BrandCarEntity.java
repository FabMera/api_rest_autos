package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entidad de MarcaCoche para la capa de persistencia
 *
 */
@Entity
@Setter
@Getter
@Table(name = "marca_coche")
@AllArgsConstructor
@NoArgsConstructor
public class BrandCarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotBlank
    private String description;

    @OneToMany(mappedBy = "brandCarEntity",orphanRemoval = true)
    private List<CarEntity> carEntityList;

}
