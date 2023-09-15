package com.project.coches.domain.service;

import com.project.coches.domain.dtos.CarDTO;
import com.project.coches.domain.repository.ICarRepository;
import com.project.coches.domain.useCase.ICarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {

    private final ICarRepository iCarRepository;

    @Override
    public List<CarDTO> getAllCars() {
        return iCarRepository.getAllCars();
    }

    @Override
    public Optional<CarDTO> getCarById(Integer idCar) {
        return iCarRepository.getCarById(idCar);
    }

    @Override
    public List<CarDTO> getCarsByBrand(Integer idBrandCar) {
        return iCarRepository.getCarsByBrand(idBrandCar);
    }

    @Override
    public List<CarDTO> getCarsByPriceLessThan(Double price) {
        return iCarRepository.getCarsByPriceLessThan(price);
    }

    @Override
    public CarDTO saveCar(CarDTO newCarDTO) {
        return iCarRepository.saveCar(newCarDTO);
    }

    @Override
    public boolean deleteCarById(Integer idCar) {
        if (iCarRepository.getCarById(idCar).isEmpty()) {
            return false;
        }
        iCarRepository.deleteCarById(idCar);
        return true;
    }
}
