package com.spring.professional.exam.tutorial.module06.question02.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.spring.professional.exam.tutorial.module06.question02.dao.CustomersDao;
import com.spring.professional.exam.tutorial.module06.question02.dao.DepartmentsDao;
import com.spring.professional.exam.tutorial.module06.question02.dao.EmployeesDao;
import com.spring.professional.exam.tutorial.module06.question02.ds.Customer;
import com.spring.professional.exam.tutorial.module06.question02.ds.Department;
import com.spring.professional.exam.tutorial.module06.question02.ds.Employee;


@Configuration
public class MockDataSourceConfig {

	@Autowired
	private EmployeesDao employeesDao;
	
	@Autowired
	private CustomersDao customersDao;
	
	@Autowired
	private DepartmentsDao departmentsDao;
	
	@PostConstruct
	public void init() {
		employeesDao.save(new Employee(1, "william", "assoni", "84389471", "frotmmaninger", "FF"));
		employeesDao.save(new Employee(2, "mock1", "mock1", "84389471", "frotmmaninger", "GG"));
		employeesDao.save(new Employee(3, "mock2", "mock2", "84389471", "frotmmaninger", "TT"));
		employeesDao.save(new Employee(4, "mock3", "mock3", "84389471", "frotmmaninger", "FF"));
		employeesDao.save(new Employee(5, "mock4", "mock4", "84389471", "frotmmaninger", "TT"));
		
		customersDao.save(new Customer(1, "CC", "Caitlin", "190 Buttonwood Dr. Olympia, WA 98512", null));
		customersDao.save(new Customer(2, "TI", "William", "190 Buttonwood Dr. Olympia, WA 98512", null));
		customersDao.save(new Customer(3, "QA", "Olaf", "190 Buttonwood Dr. Olympia, WA 98512", null));
		
		departmentsDao.save(new Department(1, "TI", "development", "GE"));
		departmentsDao.save(new Department(2, "RH", "human resources", "GE"));
	}
}
