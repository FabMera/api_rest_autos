package com.project.coches.persistance.repository;

import com.project.coches.domain.dtos.CarPurchaseRespDTO;
import com.project.coches.domain.dtos.PurchaseBillRespDTO;
import com.project.coches.domain.dtos.PurchaseReqDTO;
import com.project.coches.domain.dtos.PurchaseRespDTO;
import com.project.coches.domain.repository.IPurchaseRepository;
import com.project.coches.mapper.PurchaseReqConverter;
import com.project.coches.persistance.crud.IPurchaseCrudRepository;
import com.project.coches.persistance.entity.PurchaseEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PurchaseRepository implements IPurchaseRepository {

    private final IPurchaseCrudRepository iPurchaseCrudRepository;
    private final PurchaseReqConverter purchaseReqConverter;

    @Override
    @Transactional
    public List<PurchaseRespDTO> getAllPurchases() {
        List<PurchaseEntity> purchaseEntityList = iPurchaseCrudRepository.findAll();
        List<PurchaseRespDTO> listPurchaseRespDTO = new ArrayList<>();
        purchaseEntityList.forEach(purchaseEntity -> listPurchaseRespDTO.add(toPurchaseRespDTOEntity(purchaseEntity)));
        return listPurchaseRespDTO;

    }

    @Override
    public List<PurchaseRespDTO> getByIdCustomer(String idCustomer) {
        //Creo un objeto de tipo PurchaseEntity y le asigno el resultado de la consulta.
        //Busco en la tabla de compras por el id del cliente.
        //Creo un arraylist de PurchaseRespDTO.
        //Recorro la lista de PurchaseEntity y por cada elemento de la lista, lo convierto a PurchaseRespDTO y lo agrego a la lista de PurchaseRespDTO.
        //Retorno la lista de PurchaseRespDTO.
        List<PurchaseEntity> purchaseEntityList = iPurchaseCrudRepository.findAllByCardIdCustomer(idCustomer);
        List<PurchaseRespDTO> listPurchaseRespDTO = new ArrayList<>();
        purchaseEntityList.forEach(purchaseEntity -> listPurchaseRespDTO.add(toPurchaseRespDTOEntity(purchaseEntity)));
        return listPurchaseRespDTO;

    }

    @Override
    public PurchaseRespDTO getByNumberBill(Integer numberBill) {
        Optional<PurchaseEntity> purchaseEntity = iPurchaseCrudRepository.findById(numberBill);
        if (purchaseEntity.isPresent()) {
            return toPurchaseRespDTOEntity(purchaseEntity.get());
        }
        return null;
    }

    @Override
    public PurchaseBillRespDTO savePurchase(PurchaseReqDTO newPurchaseDTO) {
        PurchaseEntity purchaseEntity = purchaseReqConverter.convertDtoToEntity(newPurchaseDTO);
        purchaseEntity.getCarsPurchaseDTO().forEach(carPurchaseEntity -> carPurchaseEntity.setPurchaseEntity(purchaseEntity));
        PurchaseEntity purchaseEntitySave = iPurchaseCrudRepository.save(purchaseEntity);
        return new PurchaseBillRespDTO(purchaseEntitySave.getNumberBill());
    }



    public PurchaseRespDTO toPurchaseRespDTOEntity(PurchaseEntity purchaseEntity) {
        PurchaseRespDTO purchaseRespDTO = new PurchaseRespDTO();
        purchaseRespDTO.setNumberBill(purchaseEntity.getNumberBill());
        purchaseRespDTO.setCardIdCustomer(purchaseEntity.getCardIdCustomer());
        purchaseRespDTO.setDateBill(purchaseEntity.getDateBill());
        purchaseRespDTO.setTotal(purchaseEntity.getTotal());
        purchaseRespDTO.setPaymentMethod(purchaseEntity.getPaymentMethod());

        List<CarPurchaseRespDTO> carPurchaseRespDTOList = new ArrayList<>();
        purchaseEntity.getCarsPurchaseDTO().forEach(carPurchaseEntity -> {
            String reference = carPurchaseEntity.getCarEntity().getReference();
            Integer quantity = carPurchaseEntity.getQuantity();
            Integer total = carPurchaseEntity.getTotalCars();
            carPurchaseRespDTOList.add(new CarPurchaseRespDTO(reference, quantity, total));
        });
        purchaseRespDTO.setCarsPurchaseRespDTO(carPurchaseRespDTOList);
        return purchaseRespDTO;
    }
}
