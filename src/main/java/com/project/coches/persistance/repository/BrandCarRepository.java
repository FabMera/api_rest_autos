package com.project.coches.persistance.repository;

import com.project.coches.domain.dtos.BrandCarDTO;
import com.project.coches.domain.repository.IBrandCarRepository;
import com.project.coches.mapper.BrandCarConverter;
import com.project.coches.persistance.crud.IBrandCarCrudRepository;
import com.project.coches.persistance.entity.BrandCarEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class BrandCarRepository implements IBrandCarRepository {

    private final IBrandCarCrudRepository iBrandCarCrudRepository;
    private final BrandCarConverter brandCarConverter;

    @Override
    public List<BrandCarDTO> getAllBrands() {
        List<BrandCarEntity> brandCarEntityList = iBrandCarCrudRepository.findAll();
        return brandCarEntityList
                .stream()
                .map(brandCarEntity -> brandCarConverter.convertEntityToDto(brandCarEntity)).toList();
    }

    @Override
    @Transactional
    public Optional<BrandCarDTO> getBrandCarById(Long id) {
        return iBrandCarCrudRepository.findById(id)
                .map(brandCarEntity -> brandCarConverter.convertEntityToDto(brandCarEntity));
    }

    @Override
    @Transactional
    public BrandCarDTO saveBrandCar(BrandCarDTO newBrandCarDTO) {
        BrandCarEntity brandCarEntity = brandCarConverter.convertDtoToEntity(newBrandCarDTO);
        brandCarEntity=iBrandCarCrudRepository.save(brandCarEntity);
        return brandCarConverter.convertEntityToDto(brandCarEntity);
    }

    @Override
    @Transactional
    public boolean deleteBrandCarById(Long id) {
        iBrandCarCrudRepository.deleteById(id);
        return true;
    }
}
