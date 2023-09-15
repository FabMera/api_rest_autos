package com.project.coches.persistance.crud;

import com.project.coches.persistance.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerCrudRepository extends JpaRepository<CustomerEntity, String> {
    //Buscar por email
    //Query methods creado por Spring Data JPA. No es necesario implementarlos
    Optional<CustomerEntity> findByEmail(String email);
}
