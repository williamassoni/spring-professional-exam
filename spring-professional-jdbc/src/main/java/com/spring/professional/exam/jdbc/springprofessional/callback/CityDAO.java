package com.spring.professional.exam.jdbc.springprofessional.callback;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CityDAO {
	private final JdbcTemplate jdbcTemplate;
	
	//plain insert
	public void addCity(final City city) {
		jdbcTemplate.update("insert into city(id, name) values (?,?)", city.getId(), city.getName());
	}
	
	//plain insert
	public void addCityPreparedStatementCallback(final City city) {
		jdbcTemplate.execute("insert into city(id,name) values (?,?)", new PreparedStatementCallback<Void>() {
			@Override
			public Void doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, city.getId());
				ps.setString(2, city.getName());

				return null;
			}
		});
	}
	
	//Sample using RowMapper
	public List<City> fetchAllCitiesUsingRowMapper(){
		return jdbcTemplate.query("select * from city", (rs, rowNum)-> City.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
	}
	
	//Sample using RowCallBackHandler
	public List<City> fetchAllCitiesUsingRowCallBackHandler() {
		final List<City> aggregate = new ArrayList<>();
		
		jdbcTemplate.query("select * from city", (rs) -> { aggregate.add(City.builder().id(rs.getInt("id")).name(rs.getNString("name")).build()); });
		
		return aggregate;
	}
	
	//Sample using ResultSetExtractor
	public List<City> fetchAllCitiesUsingResultSetExtractor() {
		return jdbcTemplate.query("select * from city", 
								 (rs) -> {
									 final List<City> aggregate = new ArrayList<>();
									 while (rs.next()) {
										 aggregate.add(City.builder().id(rs.getInt("id")).name(rs.getNString("name")).build());
									 }
			
									 return aggregate;
								 });
	}
	
	//sample using PreparedStatementCreator
	public List<City> fetchAllCitiesUsingPrepareStatmentCreator() {
		return jdbcTemplate.query((conn) -> conn.prepareStatement("select * from city"), (rs, rowNum) -> City.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
	}
	
	//sample using PreparedStatementSetter
	public List<City> fetchAllCitiesUsingPrepareStatmentSetter() {
		return jdbcTemplate.query("select * from city where id = ?", 
								  (ps) -> {ps.setInt(1, 1); },
								  (rs, rowNumber) -> City.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
	}
	
	//sample using PreparedStatementCreator/PreparedStatementSetter
	public List<City> fetchAllCitiesUsingPrepareStatmentCreatorAndPrepareStatmentSetter() {
		return jdbcTemplate.query((conn) -> conn.prepareStatement("select * from city where id=?"),
								  (ps) -> { ps.setInt(1, 1);},
								  (rs) -> {
									final List<City> aggregate = new ArrayList<>();
									while (rs.next()) {
										aggregate.add(City.builder().id(rs.getInt("id")).name(rs.getNString("name")).build());
									}
									
									return aggregate;
								  });
	}
}
