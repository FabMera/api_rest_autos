package com.project.coches.persistance.repository;

import com.project.coches.domain.dtos.CarDTO;
import com.project.coches.domain.repository.ICarRepository;
import com.project.coches.mapper.CarClassConverter;
import com.project.coches.persistance.crud.ICarCrudRepository;
import com.project.coches.persistance.entity.CarEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CarRepository implements ICarRepository {

    private final ICarCrudRepository iCarCrudRepository;
    private final CarClassConverter carClassConverter;

    @Override
    @Transactional
    public List<CarDTO> getAllCars() {
        List<CarEntity> carEntityList = iCarCrudRepository.findAll();
        return carEntityList
                .stream()
                .map(carEntity -> carClassConverter.convertEntityToDto(carEntity)).toList();
    }

    @Override
    @Transactional
    public Optional<CarDTO> getCarById(Integer idCar) {
        return iCarCrudRepository.findById(idCar)
                .map(carEntity -> carClassConverter.convertEntityToDto(carEntity));
    }

    @Override
    public List<CarDTO> getCarsByBrand(Integer idBrandCar) {
        return iCarCrudRepository.getAllByBrandCarId(idBrandCar)
                .stream()
                .map(carEntity -> carClassConverter.convertEntityToDto(carEntity)).toList();
    }

    @Override
    public List<CarDTO> getCarsByPriceLessThan(Double price) {
        return iCarCrudRepository.getAllByPriceLessThan(price)
                .stream()
                .map(carEntity -> carClassConverter.convertEntityToDto(carEntity)).toList();
    }

    @Override
    public CarDTO saveCar(CarDTO newCarDTO) {
        CarEntity carEntity = carClassConverter.convertDtoToEntity(newCarDTO);
        carEntity = iCarCrudRepository.save(carEntity);
        return carClassConverter.convertEntityToDto(carEntity);
    }

    @Override
    public boolean deleteCarById(Integer idCar) {
        iCarCrudRepository.deleteById(idCar);
        return true;
    }

}
