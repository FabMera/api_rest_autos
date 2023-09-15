package com.project.coches.mapper;

import com.project.coches.domain.dtos.PurchaseReqDTO;
import com.project.coches.persistance.entity.PurchaseEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseReqConverter {
    private final ModelMapper modelMapper;

    public PurchaseReqDTO convertEntityToDto(PurchaseEntity purchaseEntity) {
        return modelMapper.map(purchaseEntity, PurchaseReqDTO.class);
    }

    public PurchaseEntity convertDtoToEntity(PurchaseReqDTO purchaseReqDTO) {
        return modelMapper.map(purchaseReqDTO, PurchaseEntity.class);
    }
}
