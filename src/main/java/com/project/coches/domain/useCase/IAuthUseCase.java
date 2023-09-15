package com.project.coches.domain.useCase;

import com.project.coches.domain.dtos.AuthCustomerDTO;
import com.project.coches.domain.dtos.JwtResponseDTO;

public interface IAuthUseCase {
    JwtResponseDTO signIn(AuthCustomerDTO authCustomerDTO);
    JwtResponseDTO signOut(String jwt);
}
