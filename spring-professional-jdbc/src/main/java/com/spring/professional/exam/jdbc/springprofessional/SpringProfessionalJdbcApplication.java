package com.spring.professional.exam.jdbc.springprofessional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.spring.professional.exam.jdbc.springprofessional.callback.City;
import com.spring.professional.exam.jdbc.springprofessional.callback.CityDAO;
import com.spring.professional.exam.jdbc.springprofessional.transaction.CompanyService;
import com.spring.professional.exam.jdbc.springprofessional.user.UserEntity;
import com.spring.professional.exam.jdbc.springprofessional.user.UserRepository;
import com.spring.professional.exam.jdbc.springprofessional.user.UserService;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class})
public class SpringProfessionalJdbcApplication implements CommandLineRunner, ApplicationRunner{

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CityDAO cityDAO;
	
	@Autowired
	private CompanyService companyService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringProfessionalJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		companyService.run();
		
		
		final UserRepository entity = context.getBean(UserRepository.class);
		
		entity.save(new UserEntity(1L, "William", 30L));
		entity.save(new UserEntity(2L, "William2", 30L));
		entity.save(new UserEntity(3L, "William", 30L));
		
		entity.findAll();
		entity.findByNameMode1("William");
		entity.findByNameMode2("William");
		entity.findByNameMode3(10L, "William");
		entity.findByNameAndAgeGreaterThanEqual("William", 30L);
		entity.findAllByNameStartingWith("W");
		
		entity.findFirstTwoByName("William");
		entity.findAllByName("William");
		
		//entity.findDistincUserEntityNameByName("William");
		entity.findDistincAgeByNameIsNotNull();
		DataSourceUtils.getConnection(dataSource);
		
		UserService user = context.getBean(UserService.class);
		user.saveUser();
		
		
		cityDAO.addCity(new City(1, "Florianopolis"));
		cityDAO.addCityPreparedStatementCallback(new City(2, "Munich"));
		
		cityDAO.fetchAllCitiesUsingRowMapper();
		cityDAO.fetchAllCitiesUsingRowCallBackHandler();
		cityDAO.fetchAllCitiesUsingResultSetExtractor();
		cityDAO.fetchAllCitiesUsingPrepareStatmentCreator();
		cityDAO.fetchAllCitiesUsingPrepareStatmentSetter();
		cityDAO.fetchAllCitiesUsingPrepareStatmentCreatorAndPrepareStatmentSetter();
		
		
		companyService.run();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.err.println("");
	}

}
