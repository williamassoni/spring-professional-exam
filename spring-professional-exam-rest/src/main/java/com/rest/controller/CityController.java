package com.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.CityEntity;
import com.rest.model.CityModel;
import com.rest.repository.CityRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController implements CommandLineRunner{
	
	private CityRepository cityRepository;
	
	@GetMapping
	public CollectionModel<CityModel> findAll(){
		final List<CityEntity> allCities = cityRepository.findAll();
		final List<CityModel> cities = allCities.stream()
												.map(c-> {
													final CityModel model = CityModel.builder().name(c.getName()).build();
													model.add(linkTo(methodOn(CityController.class).one(c.getId())).withSelfRel());
												//	model.add(linkTo(methodOn(CityController.class).delete(c.getId())).withRel("delete"));
													
													//model.add(linkTo(methodOn(CityController.class).delete(c.getId())).withSelfRel());
													
													return model;
												})
												.collect(Collectors.toList());
		
		return CollectionModel.of(cities);
	}
	
	@GetMapping("/{id}")
	public EntityModel<CityModel> one(@PathVariable Long id) {
		final Optional<CityEntity> city = cityRepository.findById(id);
		final CityModel model = new CityModel(city.get().getName());
		
		return EntityModel.of(model,
					linkTo(methodOn(CityController.class).one(id)).withSelfRel(),
					linkTo(methodOn(CityController.class).findAll()).withRel("cities"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public void run(String... args) throws Exception {
		cityRepository.save(new CityEntity(1L, "Florianopolis"));
		cityRepository.save(new CityEntity(2L, "Münich"));
	}
}
