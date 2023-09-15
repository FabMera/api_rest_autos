package com.project.coches.domain.repository;

import com.project.coches.domain.dtos.PurchaseBillRespDTO;
import com.project.coches.domain.dtos.PurchaseReqDTO;
import com.project.coches.domain.dtos.PurchaseRespDTO;

import java.util.List;

public interface IPurchaseRepository {
    List<PurchaseRespDTO> getAllPurchases();

    List<PurchaseRespDTO> getByIdCustomer(String idCustomer);

    PurchaseRespDTO getByNumberBill(Integer numberBill);

    PurchaseBillRespDTO savePurchase(PurchaseReqDTO newPurchaseDTO);
}
