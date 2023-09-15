package com.project.coches.domain.useCase;

import com.project.coches.domain.dtos.CarDTO;

import java.util.List;
import java.util.Optional;

public interface ICarService {

    List<CarDTO> getAllCars();

    Optional<CarDTO> getCarById(Integer idCar);

    List<CarDTO> getCarsByBrand(Integer idBrandCar);

    List<CarDTO> getCarsByPriceLessThan(Double price);

    CarDTO saveCar(CarDTO newCarDTO);

    boolean deleteCarById(Integer idCar);
}
