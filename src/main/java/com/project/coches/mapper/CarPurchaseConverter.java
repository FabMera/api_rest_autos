package com.project.coches.mapper;

import com.project.coches.domain.dtos.CarPurchaseReqDTO;
import com.project.coches.persistance.entity.CarPurchaseEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarPurchaseConverter {
    private final ModelMapper modelMapper;

    public CarPurchaseReqDTO convertEntityToDto(CarPurchaseEntity carPurchaseEntity) {
        return modelMapper.map(carPurchaseEntity, CarPurchaseReqDTO.class);
    }
    public CarPurchaseEntity convertDtoToEntity(CarPurchaseReqDTO carPurchaseReqDTO) {
        return modelMapper.map(carPurchaseReqDTO, CarPurchaseEntity.class);
    }

}
