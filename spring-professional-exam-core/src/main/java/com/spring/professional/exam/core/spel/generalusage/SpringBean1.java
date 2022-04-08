package com.spring.professional.exam.core.spel.generalusage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class SpringBean1 {

	@Value("#{T(com.spring.professional.exam.core.spel.generalusage.SpringBean2).STATIC_VALUE}")
	private String staticValuefromSpring2;
	
	@Value("#{'String is empty --> ' + T(com.spring.professional.exam.core.spel.generalusage.SpringBean2).isEmpty('I am not empty')}")
	private String staticMethodFromSpring2;
	
	@Value("#{@springBean2.firstName}")
	private String firstNameCommingFromSpring2;
	
	@Value("#{environment['spel.properties']}")
	private String appEnviroment;
	
	@Value("#{systemProperties['app.vm.property']}")
	private String systemProperties;
	
	@Value("#{systemEnvironment['JAVA_HOME']}")
	private String systemEnvronment;

	@Value("#{environment['defaultProfiles']}")
	private String defaultProfile;

}
