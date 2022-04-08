package com.spring.professional.exam.jdbc.springprofessional.callback;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
public class City {
	@Id
	private Integer id;
	private String name;
}
