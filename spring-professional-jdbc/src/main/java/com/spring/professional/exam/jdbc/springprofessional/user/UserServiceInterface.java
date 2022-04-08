package com.spring.professional.exam.jdbc.springprofessional.user;

import org.springframework.transaction.annotation.Transactional;

public interface UserServiceInterface {

	@Transactional	
	void saveUser();
}
