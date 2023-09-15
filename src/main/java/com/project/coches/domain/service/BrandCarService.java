package com.project.coches.domain.service;

import com.project.coches.domain.dtos.BrandCarDTO;
import com.project.coches.domain.repository.IBrandCarRepository;
import com.project.coches.domain.useCase.IBrandCarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BrandCarService implements IBrandCarService {

    private final IBrandCarRepository iBrandCarRepository;

    @Override
    @Transactional
    public List<BrandCarDTO> getAll() {
        return iBrandCarRepository.getAllBrands();
    }

    @Override
    @Transactional
    public Optional<BrandCarDTO> getBrandCar(Long id) {
        return iBrandCarRepository.getBrandCarById(id);
    }

    @Override
    @Transactional
    public BrandCarDTO saveBrandCar(BrandCarDTO newBrandCarDTO) {
        return iBrandCarRepository.saveBrandCar(newBrandCarDTO);
    }

    @Override
    @Transactional
    public boolean deleteBrandCar(Long id) {
        try {
            iBrandCarRepository.deleteBrandCarById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Optional<BrandCarDTO> updateBrandCar(BrandCarDTO newBrandCarDTO) {
        if (iBrandCarRepository.getBrandCarById(newBrandCarDTO.getId()).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(iBrandCarRepository.saveBrandCar(newBrandCarDTO));
    }
}
