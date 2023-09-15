package com.project.coches.controller;

import com.project.coches.domain.dtos.BrandCarDTO;
import com.project.coches.domain.useCase.IBrandCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/marcas-coches")
public class BrandCarController {

    private final IBrandCarService iBrandCarService;


    @GetMapping
    public ResponseEntity<List<BrandCarDTO>> getAll() {
        /*return ResponseEntity.status(HttpStatus.OK).body(iBrandCarService.getAll());*/
        return new ResponseEntity<>(iBrandCarService.getAll(), HttpStatus.OK); // Otra forma de hacerlo
        //return ResponseEntity.ok(iBrandCarService.getAll()); // Otra forma de hacerlo
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandCarDTO> getBrandCar(@PathVariable Long id) {
        return ResponseEntity.of(iBrandCarService.getBrandCar(id));
    }

    @PostMapping
    public ResponseEntity<BrandCarDTO> saveBrandCar(@RequestBody BrandCarDTO newBrandCarDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iBrandCarService.saveBrandCar(newBrandCarDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping
    //PatchMapping sirve para actualizar un solo campo de la entidad a diferencia de PutMapping que actualiza toda la entidad.
    public ResponseEntity<BrandCarDTO> update(@RequestBody BrandCarDTO brandCarDTOUpdate) {
        return ResponseEntity.of(iBrandCarService.updateBrandCar(brandCarDTOUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBrandCar(@PathVariable Long id) {
        return new ResponseEntity<>(this.iBrandCarService.deleteBrandCar(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
