package com.project.coches.persistance.crud;

import com.project.coches.persistance.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICarCrudRepository extends JpaRepository<CarEntity, Integer> {
    List<CarEntity> getAllByBrandCarId(Integer id);

    List<CarEntity> getAllByPriceLessThan(Double price);
}
