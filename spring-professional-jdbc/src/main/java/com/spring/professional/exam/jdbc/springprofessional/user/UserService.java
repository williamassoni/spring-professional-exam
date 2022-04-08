package com.spring.professional.exam.jdbc.springprofessional.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
class LocaLReference {
	
	@Transactional(propagation = Propagation.MANDATORY)
	public void saveuser2() {
		//nothing here..
		System.err.println("ok..");
	}
	
}

@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private LocaLReference reference;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void saveUser() {
		repository.save(new UserEntity(2L,"Mock", 50L));

		reference.saveuser2();
		/*
		new Thread() {
			@Override
			public void run() {
				reference.saveuser2();
			}
		}.start();*/
		
		applicationEventPublisher.publishEvent(new UserCreatedEvent());
	}
}
