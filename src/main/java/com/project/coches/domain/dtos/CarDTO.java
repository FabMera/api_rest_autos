package com.project.coches.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {

    private Integer codeCar;
    private Integer brandCarId;
    private String reference;
    private Double price;
    private String color;
    private Double modelYear;
    private Integer numberDoor;
    private String horsePower;
    private Double engineDisplacement;
    private String transmission;
    private String fuelType;
    private Integer numberSeats;
    private Integer traction;
    private String steering;
    private String category;
    private String imagePath;
    private Integer stock;


    @Override
    public String toString() {
        return "CarDTO{" +
                "codeCar=" + codeCar +
                ", brandCarId=" + brandCarId +
                ", reference='" + reference + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", modelYear=" + modelYear +
                ", numberDoor=" + numberDoor +
                ", horsePower='" + horsePower + '\'' +
                ", engineDisplacement=" + engineDisplacement +
                ", transmission='" + transmission + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", numberSeats=" + numberSeats +
                ", traction=" + traction +
                ", steering='" + steering + '\'' +
                ", category='" + category + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
