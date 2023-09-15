package com.project.coches.mapper;

import com.project.coches.domain.dtos.CarDTO;
import com.project.coches.persistance.entity.CarEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarClassConverter {

    private final ModelMapper modelMapper;

    public CarDTO convertEntityToDto(CarEntity carEntity) {
        return modelMapper.map(carEntity, CarDTO.class);
    }

    public CarEntity convertDtoToEntity(CarDTO carDTO) {
        return modelMapper.map(carDTO, CarEntity.class);
    }
}
