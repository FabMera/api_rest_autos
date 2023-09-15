package com.project.coches.controller;

import com.project.coches.domain.dtos.PurchaseBillRespDTO;
import com.project.coches.domain.dtos.PurchaseReqDTO;
import com.project.coches.domain.dtos.PurchaseRespDTO;
import com.project.coches.domain.useCase.IPurchaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {
    private final IPurchaseUseCase iPurchaseUseCase;

    @GetMapping
    public ResponseEntity<List<PurchaseRespDTO>> getAll() {
        return ResponseEntity.ok(iPurchaseUseCase.getAllPurchases());
    }

    @GetMapping("/{cardIdCustomer}")
    public ResponseEntity<List<PurchaseRespDTO>> getAllByCardIdCustomer(@PathVariable String cardIdCustomer) {
        return ResponseEntity.ok(iPurchaseUseCase.getByIdCustomer(cardIdCustomer));
    }

    @GetMapping("/bill/{numberBill}")
    public ResponseEntity<PurchaseRespDTO> getPurchasesByNumberBill(@PathVariable Integer numberBill) {
        return ResponseEntity.ok(iPurchaseUseCase.getByNumberBill(numberBill));
    }

    @PostMapping
    public ResponseEntity<PurchaseBillRespDTO> savePurchase(@RequestBody PurchaseReqDTO newPurchaseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iPurchaseUseCase.savePurchase(newPurchaseDTO));
    }
}
