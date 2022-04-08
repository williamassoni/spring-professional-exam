package com.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.FoodEntity;
import com.rest.enums.State;
import com.rest.model.FoodModel;
import com.rest.repository.FoodRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/food")
@AllArgsConstructor
public class FoodController implements CommandLineRunner{
	private FoodRepository foodRepository;
	
	@Override
	public void run(String... args) throws Exception {
		foodRepository.save(new FoodEntity(1L, "Potato", State.MATURE));
		foodRepository.save(new FoodEntity(2L, "Salad", State.ROTTEN));
	}

	@GetMapping
	public CollectionModel<FoodModel> findAll(){
		final List<FoodEntity> allfood = foodRepository.findAll();
		final List<FoodModel> allfoodModel = allfood.stream()
													.map(c-> {
														final FoodModel model = FoodModel.builder().name(c.getName()).state(c.getState().name()).build();
														model.add(linkTo(methodOn(FoodController.class).one(c.getId())).withSelfRel());
														
														if(State.MATURE.equals(c.getState())) {
															model.add(linkTo(methodOn(FoodController.class).eat(c.getId())).withRel("eat"));
														}
														
														return model;
													})
													.collect(Collectors.toList());
		
		return CollectionModel.of(allfoodModel);
	}
	
	@GetMapping("/{id}")
	public EntityModel<FoodModel> one(@PathVariable Long id) {
		final Optional<FoodEntity> city = foodRepository.findById(id);
		final FoodModel model = FoodModel.builder().name(city.get().getName()).state(city.get().getState().name()).build();
		
		return EntityModel.of(model,
					linkTo(methodOn(CityController.class).one(id)).withSelfRel(),
					linkTo(methodOn(CityController.class).findAll()).withRel("food"));
	}
	
	
	@PostMapping("/{id}/eat")
	public EntityModel<FoodModel> eat(@PathVariable Long id) {
		final Optional<FoodEntity> city = foodRepository.findById(id);
		final FoodModel model = FoodModel.builder().name(city.get().getName()).state(city.get().getState().name()).build();
		
		foodRepository.deleteById(id);
		
		return EntityModel.of(model, linkTo(methodOn(CityController.class).findAll()).withRel("food"));
	}
	
	
	@DeleteMapping("/{id}")
	public EntityModel<FoodModel> delete(@PathVariable Long id) {
		final Optional<FoodEntity> city = foodRepository.findById(id);
		final FoodModel model = FoodModel.builder().name(city.get().getName()).state(city.get().getState().name()).build();
		
		foodRepository.deleteById(id);
		
		return EntityModel.of(model,
					linkTo(methodOn(CityController.class).one(id)).withSelfRel(),
					linkTo(methodOn(CityController.class).findAll()).withRel("food"));
	}
	
	
}