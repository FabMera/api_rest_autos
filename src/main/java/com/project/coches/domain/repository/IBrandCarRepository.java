package com.project.coches.domain.repository;

import com.project.coches.domain.dtos.BrandCarDTO;

import java.util.List;
import java.util.Optional;

public interface IBrandCarRepository {
    List<BrandCarDTO> getAllBrands();

    /*nullPointerException*/
    Optional<BrandCarDTO> getBrandCarById(Long id);

    BrandCarDTO saveBrandCar(BrandCarDTO newBrandCarDTO);

    boolean deleteBrandCarById(Long id);
}
