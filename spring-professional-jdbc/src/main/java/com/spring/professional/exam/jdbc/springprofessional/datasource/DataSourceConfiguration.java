package com.spring.professional.exam.jdbc.springprofessional.datasource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class DataSourceConfiguration {

	//@Bean
	public DataSource getDriverManagerDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1");
		datasource.setUsername("sa");
		datasource.setPassword("sa");
		datasource.setDriverClassName("org.hsqldb.jdbcDriver");
		
		return datasource; 
	}
	 
	@Bean
	@Primary
	public DataSource getSmartDataSource() {
		SingleConnectionDataSource datasource = new SingleConnectionDataSource();
		
		datasource.setUrl("jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1");
		datasource.setUsername("sa");
		datasource.setPassword("sa");
		datasource.setDriverClassName("org.hsqldb.jdbcDriver");
		datasource.setSuppressClose(true);
		
		return datasource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory manager, final DataSource datasource) {
		JpaTransactionManager plataform = new JpaTransactionManager();
		plataform.setEntityManagerFactory(manager);
		plataform.setDataSource(datasource);
		
		return plataform;
	}
}
