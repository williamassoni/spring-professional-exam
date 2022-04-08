package com.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rest.entity.FoodEntity;

public interface FoodRepository extends CrudRepository<FoodEntity, Long>{

	List<FoodEntity> findAll();
}