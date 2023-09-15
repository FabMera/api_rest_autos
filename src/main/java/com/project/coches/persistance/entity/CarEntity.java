package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_car", nullable = false)
    private Integer codeCar;
    @Column(name = "brandcar_id")
    private Integer brandCarId;
    private String reference;
    private Double price;
    private String color;
    @Column(name = "model_year")
    private Double modelYear;
    @Column(name = "number_door")
    private Integer numberDoor;
    @Column(name = "hp")
    private String horsePower;
    @Column(name = "engine_displacement")
    private Double engineDisplacement;
    private String transmission;
    @Column(name = "fuel_type")
    private String fuelType;
    @Column(name = "number_seats")
    private Integer numberSeats;
    private Integer traction;
    private String steering;
    private String category;
    private String imagePath;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "brandcar_id", insertable = false, updatable = false)
    private BrandCarEntity brandCarEntity;

    @OneToMany(mappedBy = "carEntity")
    private List<CarPurchaseEntity> carPurchaseEntity;

}
