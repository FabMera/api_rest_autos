package com.project.coches.domain.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private String cardId;
    private String fullName;
    private String email;
    private Double numberCellphone;
    private Integer active;
    private String password;
    private String rol;

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "carId='" + cardId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", numberCellphone=" + numberCellphone +
                ", active=" + active +
                ", password='" + password + '\'' +
                '}';
    }
}
