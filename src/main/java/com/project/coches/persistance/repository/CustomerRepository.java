package com.project.coches.persistance.repository;

import com.project.coches.domain.dtos.CustomerDTO;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.mapper.CustomerConverter;
import com.project.coches.persistance.crud.ICustomerCrudRepository;
import com.project.coches.persistance.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository implements ICustomerRepository {

    private final ICustomerCrudRepository iCustomerCrudRepository;
    private final CustomerConverter customerConverter;


    @Override
    @Transactional
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerEntity> customerEntityList = iCustomerCrudRepository.findAll();
        return customerEntityList
                .stream()
                .map(customerEntity ->customerConverter.convertEntityToDto(customerEntity)).toList();
    }

    @Override
    @Transactional
    public Optional<CustomerDTO> getCustomerById(String cardId) {
        return iCustomerCrudRepository.findById(cardId).map(customerEntity -> customerConverter.convertEntityToDto(customerEntity));
    }

    @Override
    @Transactional
    public Optional<CustomerDTO> getCustomerByEmail(String email) {
        return iCustomerCrudRepository.findByEmail(email).map((customerEntity -> customerConverter.convertEntityToDto(customerEntity)));
    }

    @Override
    @Transactional
    //Aca recibe un CustomerDTO y hay que pasarlo a CustomerEntity.
    public CustomerDTO saveCustomer(CustomerDTO newCustomerDTO) {
        CustomerEntity customerEntity = customerConverter.convertDtoToEntity(newCustomerDTO);
        customerEntity= iCustomerCrudRepository.save(customerEntity);
        return customerConverter.convertEntityToDto(customerEntity);
    }

    @Override
    @Transactional
    public boolean deleteCustomerById(String cardId) {
        iCustomerCrudRepository.deleteById(cardId);
        return true;
    }
}
