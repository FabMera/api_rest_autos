package com.project.coches.domain.service;

import com.project.coches.domain.dtos.CarDTO;
import com.project.coches.domain.dtos.PurchaseBillRespDTO;
import com.project.coches.domain.dtos.PurchaseReqDTO;
import com.project.coches.domain.dtos.PurchaseRespDTO;
import com.project.coches.domain.repository.ICarRepository;
import com.project.coches.domain.repository.IPurchaseRepository;
import com.project.coches.domain.useCase.IPurchaseUseCase;
import com.project.coches.exceptions.PurchaseNoExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService implements IPurchaseUseCase {


    private final IPurchaseRepository iPurchaseRepository;
    private final ICarRepository iCarRepository;

    @Override
    public List<PurchaseRespDTO> getAllPurchases() {
        return iPurchaseRepository.getAllPurchases();
    }

    @Override
    public List<PurchaseRespDTO> getByIdCustomer(String idCustomer) {
        return iPurchaseRepository.getByIdCustomer(idCustomer);
    }

    @Override
    public PurchaseRespDTO getByNumberBill(Integer numberBill) {
        PurchaseRespDTO purchaseRespDTO = iPurchaseRepository.getByNumberBill(numberBill);
        if (purchaseRespDTO == null) {
            throw new PurchaseNoExistsException();
        }
        return purchaseRespDTO;
    }

    @Override
    public PurchaseBillRespDTO savePurchase(PurchaseReqDTO newPurchaseDTO) {
        PurchaseBillRespDTO purchaBillResponse = iPurchaseRepository.savePurchase(newPurchaseDTO);
        newPurchaseDTO.getCarsPurchaseDTO().forEach(carPurchase -> {
            CarDTO car = iCarRepository.getCarById(carPurchase.getCodeCar()).get();
            car.setStock(car.getStock() - carPurchase.getQuantity());
            iCarRepository.saveCar(car);
        });
        return purchaBillResponse;
    }
}
