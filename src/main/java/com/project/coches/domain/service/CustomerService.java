package com.project.coches.domain.service;

import com.project.coches.domain.dtos.CustomerDTO;
import com.project.coches.domain.dtos.ResponseCustomerDTOPass;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.domain.useCase.ICustomerService;
import com.project.coches.exceptions.EmailValidationException;
import com.project.coches.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository iCustomerRepository;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return iCustomerRepository.getAllCustomers();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(String carId) {
        return iCustomerRepository.getCustomerById(carId);
    }

    @Override
    public Optional<CustomerDTO> getCustomerByEmail(String email) {
        return iCustomerRepository.getCustomerByEmail(email);
    }

    @Override
    public ResponseCustomerDTOPass saveCustomer(CustomerDTO newCustomerDTO) {

        String expresionRegular = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        if (!newCustomerDTO.getEmail().matches(expresionRegular)) {
            throw new EmailValidationException();
        }
        String pass = generateRandomPassword(8);
        newCustomerDTO.setPassword(pass);
        newCustomerDTO.setActive(1);
        newCustomerDTO.setRol(Roles.CUSTOMER);
        iCustomerRepository.saveCustomer(newCustomerDTO);
        return new ResponseCustomerDTOPass(pass);
    }

    @Override
    public Optional<CustomerDTO> updateCustomer(CustomerDTO newCustomerDTO) {
        if (iCustomerRepository.getCustomerById(newCustomerDTO.getCardId()).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(iCustomerRepository.saveCustomer(newCustomerDTO));
    }

    @Override
    public boolean deleteCustomerById(String cardId) {
        if (iCustomerRepository.getCustomerById(cardId).isEmpty()) {
            return false;
        }
        iCustomerRepository.deleteCustomerById(cardId);
        return true;
    }

    //Metodo para generar un pass
    private String generateRandomPassword(int len) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }
}
