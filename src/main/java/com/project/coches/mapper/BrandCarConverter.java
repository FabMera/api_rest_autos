package com.project.coches.mapper;

import com.project.coches.domain.dtos.BrandCarDTO;
import com.project.coches.persistance.entity.BrandCarEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandCarConverter {
    private final ModelMapper modelMapper;

    public BrandCarDTO convertEntityToDto(BrandCarEntity brandCarEntity) {
        return modelMapper.map(brandCarEntity, BrandCarDTO.class);
    }

    public BrandCarEntity convertDtoToEntity(BrandCarDTO brandCarDTO) {
        return modelMapper.map(brandCarDTO, BrandCarEntity.class);
    }

}
