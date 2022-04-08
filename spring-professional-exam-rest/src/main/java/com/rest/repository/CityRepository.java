package com.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rest.entity.CityEntity;

public interface CityRepository extends CrudRepository<CityEntity, Long>{

	List<CityEntity> findAll();
}