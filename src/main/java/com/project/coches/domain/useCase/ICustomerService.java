package com.project.coches.domain.useCase;

import com.project.coches.domain.dtos.CustomerDTO;
import com.project.coches.domain.dtos.ResponseCustomerDTOPass;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> getCustomerById(String cardId);

    Optional<CustomerDTO> getCustomerByEmail(String email);

    ResponseCustomerDTOPass saveCustomer(CustomerDTO newCustomerDTO);

    Optional<CustomerDTO> updateCustomer(CustomerDTO newCustomerDTO);

    boolean deleteCustomerById(String cardId);
}
