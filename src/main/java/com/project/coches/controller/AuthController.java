package com.project.coches.controller;

import com.project.coches.domain.dtos.AuthCustomerDTO;
import com.project.coches.domain.dtos.CustomerDTO;
import com.project.coches.domain.dtos.JwtResponseDTO;
import com.project.coches.domain.dtos.ResponseCustomerDTOPass;
import com.project.coches.domain.useCase.IAuthUseCase;
import com.project.coches.domain.useCase.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")

public class AuthController {

    private final IAuthUseCase authUseCase;
    private final ICustomerService customerService;


    //Responde con un ResponseCustomerDTOPass que contiene el password generado aleatoriamente
    @PostMapping("/register")
    public ResponseEntity<ResponseCustomerDTOPass> saveCustomer(@Valid @RequestBody CustomerDTO newCustomerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(newCustomerDTO));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponseDTO> signIn(@RequestBody AuthCustomerDTO authCustomerDTO) {
        return ResponseEntity.ok(authUseCase.signIn(authCustomerDTO));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<JwtResponseDTO> signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String jwt) {
        return ResponseEntity.ok(authUseCase.signOut(jwt));
    }
}
