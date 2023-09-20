package com.project.coches.controller;

import com.project.coches.domain.dtos.CustomerDTO;
import com.project.coches.domain.dtos.ResponseCustomerDTOPass;
import com.project.coches.domain.useCase.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final ICustomerService iCustomerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return new ResponseEntity<>(iCustomerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CustomerDTO> getOneCustomer(@PathVariable String cardId) {
        return ResponseEntity.of(iCustomerService.getCustomerById(cardId));
    }


    @PatchMapping
    public ResponseEntity<CustomerDTO> update( @Valid @RequestBody CustomerDTO customerDTOUpdate) {
        return ResponseEntity.of(iCustomerService.updateCustomer(customerDTOUpdate));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDTO> getCustomerEmail(@PathVariable String email) {
        return ResponseEntity.of(iCustomerService.getCustomerByEmail(email));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable String cardId) {
        return new ResponseEntity<>(iCustomerService.deleteCustomerById(cardId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
