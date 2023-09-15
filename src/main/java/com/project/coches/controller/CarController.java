package com.project.coches.controller;

import com.project.coches.domain.dtos.CarDTO;
import com.project.coches.domain.useCase.ICarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final ICarService iCarService;

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAll() {
        return new ResponseEntity<>(iCarService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getOneCar(@PathVariable Integer id) {
        return ResponseEntity.of(iCarService.getCarById(id));
    }

    @PostMapping
    public ResponseEntity<CarDTO> saveCar(@RequestBody CarDTO newCarDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCarService.saveCar(newCarDTO));
    }

    @GetMapping("/brand-car/{idBrandCar}")
    public ResponseEntity<List<CarDTO>> getCarsByBrand(@PathVariable Integer idBrandCar) {
        return ResponseEntity.ok(iCarService.getCarsByBrand(idBrandCar));
    }

    @GetMapping("/price/{priceCar}")
    public ResponseEntity<List<CarDTO>> getCarsByPrice(@PathVariable Double priceCar) {
        return ResponseEntity.ok(iCarService.getCarsByPriceLessThan(priceCar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable Integer id) {
        return new ResponseEntity<>(iCarService.deleteCarById(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
