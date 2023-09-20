package com.project.coches.domain.service;

import com.project.coches.domain.dtos.CustomerDTO;
import com.project.coches.domain.dtos.ResponseCustomerDTOPass;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.domain.useCase.ICustomerService;
import com.project.coches.exceptions.EmailAlreadyExistsException;
import com.project.coches.exceptions.EmailValidationException;
import com.project.coches.security.Roles;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository iCustomerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<CustomerDTO> getAllCustomers() {
        return iCustomerRepository.getAllCustomers();
    }

    @Override
    @Transactional
    public Optional<CustomerDTO> getCustomerById(String carId) {
        return iCustomerRepository.getCustomerById(carId);
    }

    @Override
    @Transactional
    public Optional<CustomerDTO> getCustomerByEmail(String email) {
        return iCustomerRepository.getCustomerByEmail(email);
    }

    @Override
    @Transactional
    public ResponseCustomerDTOPass saveCustomer(CustomerDTO newCustomerDTO) {
        String expresionRegular = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Optional<CustomerDTO> customerDTO = iCustomerRepository.getCustomerByEmail(newCustomerDTO.getEmail());

        if (customerDTO.isPresent()) {
            throw new EmailAlreadyExistsException("El email ya existe en la base de datos");
        }
        if (!newCustomerDTO.getEmail().matches(expresionRegular)) {
            throw new EmailValidationException();
        }
        String passBC = passwordEncoder.encode(newCustomerDTO.getPassword());
        newCustomerDTO.setPassword(passBC);
        newCustomerDTO.setActive(1);
        newCustomerDTO.setRol(Roles.CUSTOMER);
        iCustomerRepository.saveCustomer(newCustomerDTO);
        return new ResponseCustomerDTOPass("Registro realizado con exito");
    }

    @Override
    @Transactional
    public Optional<CustomerDTO> updateCustomer(CustomerDTO newCustomerDTO) {
        if (iCustomerRepository.getCustomerById(newCustomerDTO.getCardId()).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(iCustomerRepository.saveCustomer(newCustomerDTO));
    }

    @Override
    @Transactional
    public boolean deleteCustomerById(String cardId) {
        if (iCustomerRepository.getCustomerById(cardId).isEmpty()) {
            return false;
        }
        iCustomerRepository.deleteCustomerById(cardId);
        return true;
    }

    //Metodo para generar un pass
   /* private String generateRandomPassword(int len) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }*/
}
