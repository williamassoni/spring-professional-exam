package com.rest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.rest.enums.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FoodEntity {
	@Id
	private Long id;
	private String name;
	private State state;
}
