package com.project.coches.domain.service;

import com.project.coches.domain.dtos.AuthCustomerDTO;
import com.project.coches.domain.dtos.CustomerDTO;
import com.project.coches.domain.dtos.JwtResponseDTO;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.domain.useCase.IAuthUseCase;
import com.project.coches.exceptions.CustomerNotExistException;
import com.project.coches.exceptions.PassIncorrectException;
import com.project.coches.security.JWTAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthUseCase {

    private final ICustomerRepository customerRepository;
    private final JWTAuthenticationProvider jwtAuthenticationProvider;
    private final PasswordEncoder bCryptPasswordEncoder;

    //Verificamos las credenciales que recibimos del cliente y si son correctas, generamos un token
    @Override
    public JwtResponseDTO signIn(AuthCustomerDTO authCustomerDTO) {
        Optional<CustomerDTO> customer = customerRepository.getCustomerByEmail(authCustomerDTO.getEmail());
        if (customer.isEmpty()) {
            throw new CustomerNotExistException();
        }
        if (!bCryptPasswordEncoder.matches(authCustomerDTO.getPassword(), customer.get().getPassword())) {
            throw new PassIncorrectException();
        }
        return new JwtResponseDTO(jwtAuthenticationProvider.createToken(customer.get()));
    }

    @Override
    public JwtResponseDTO signOut(String jwt) {
        String[] jwtSplit = jwt.split(" ");
        return new JwtResponseDTO(jwtAuthenticationProvider.eliminarToken(jwtSplit[1]));
    }
}
