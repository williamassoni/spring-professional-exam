package com.spring.professional.exam.core.spel.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class SpringBean1 {

	@Value("#{'frottmaningerstrassse'.toString()}")
	private String streetName;
	
	@Value("#{@springBean2.isLowerThan10 ? 10: 10*3 }")
	private Long accountBalance;
}
