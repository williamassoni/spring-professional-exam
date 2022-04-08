package com.spring.professional.exam.jdbc.springprofessional.user;

import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent{

	public UserCreatedEvent() {
		super(UserCreatedEvent.class);
	}

	private static final long serialVersionUID = 1L;

}
