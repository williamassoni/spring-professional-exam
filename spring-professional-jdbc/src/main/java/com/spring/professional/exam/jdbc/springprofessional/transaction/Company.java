package com.spring.professional.exam.jdbc.springprofessional.transaction;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company {
	
	@Id
	private Long id;
	private String name;
}
