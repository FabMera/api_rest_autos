package com.project.coches.domain.repository;

import com.project.coches.domain.dtos.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {
    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> getCustomerById(String cardId);

    Optional<CustomerDTO> getCustomerByEmail(String email);

    CustomerDTO saveCustomer(CustomerDTO newCustomerDTO);

    boolean deleteCustomerById(String cardId);
}
