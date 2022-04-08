package com.spring.professional.exam.core.spel.valueusage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ScalarLiteralBean {
	
	@Value("John")
    private String name;

	@Value("true")
    private boolean accountExists;
	
    @Value("100")
    private int idNumber;
    
    @Value("100.0")
    private Double idNumberDouble;
    
    @Value("#{'Wall Street'.toUpperCase()}")
    private String streetName;

    @Value("#{10 * 2}")
    private float accountBalance;

    @Value("#{'potato'.toString()+' - '}")
    private String departmentName;

    private String managerName;

    @Value("${app.dependent.departments}")
    private String[] dependentDepartments;

    @Value("#{new String[]{'potato','potato2'}[0]}")
    private String result;
    
    private String fixedValue;
    
    public ScalarLiteralBean(@Value("This value is fixed") final String fixedValue) {
    	this.fixedValue = fixedValue;
    }
    
    @Autowired
    @Value("this is hardcoded and will be apply to all of them")
    public void secondHardCodedFixedValue(final String param1, final String param2) {
    	System.err.println(param1);
    	System.err.println(param2);
    }
    
}
