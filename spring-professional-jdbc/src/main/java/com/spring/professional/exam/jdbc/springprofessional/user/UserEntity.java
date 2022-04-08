package com.spring.professional.exam.jdbc.springprofessional.user;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

	@Id
	private Long id;
	
	private String name;
	
	private Long age;
}
