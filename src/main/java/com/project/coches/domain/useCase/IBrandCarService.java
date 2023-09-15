package com.project.coches.domain.useCase;

import com.project.coches.domain.dtos.BrandCarDTO;

import java.util.List;
import java.util.Optional;

public interface IBrandCarService {
    List<BrandCarDTO> getAll();

    Optional<BrandCarDTO> getBrandCar(Long id);

    BrandCarDTO saveBrandCar(BrandCarDTO newBrandCarDTO);

    boolean deleteBrandCar(Long id);

    Optional<BrandCarDTO> updateBrandCar(BrandCarDTO brandCarDTOUpdate);
}
