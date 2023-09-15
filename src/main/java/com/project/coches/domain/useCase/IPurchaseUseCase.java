package com.project.coches.domain.useCase;

import com.project.coches.domain.dtos.PurchaseBillRespDTO;
import com.project.coches.domain.dtos.PurchaseReqDTO;
import com.project.coches.domain.dtos.PurchaseRespDTO;

import java.util.List;

public interface IPurchaseUseCase {
    List<PurchaseRespDTO> getAllPurchases();

    List<PurchaseRespDTO> getByIdCustomer(String idCustomer);

    PurchaseRespDTO getByNumberBill(Integer numberBill);

    PurchaseBillRespDTO savePurchase(PurchaseReqDTO newPurchaseDTO);

}
