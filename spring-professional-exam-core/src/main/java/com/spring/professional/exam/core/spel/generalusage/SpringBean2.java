package com.spring.professional.exam.core.spel.generalusage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class SpringBean2 {

	@Value("#{'fröttmaninger straße'}")
	private String firstName;
	
	public static final String STATIC_VALUE = "OK!";
	
	public final static boolean isEmpty(final String param) {
		return StringUtils.hasText(param);
	}
	
	
}
