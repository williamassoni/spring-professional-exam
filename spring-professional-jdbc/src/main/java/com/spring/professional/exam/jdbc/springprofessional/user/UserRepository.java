package com.spring.professional.exam.jdbc.springprofessional.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

	@Query("select u from UserEntity u where u.name = ?1")
	List<UserEntity> findByNameMode1(final String name);
	
	@Query("select u from UserEntity u where u.name = :name")
	List<UserEntity> findByNameMode2(@Param("name") final String name);
	
	@Query("select u from UserEntity u where u.name = ?2 and u.age >= ?1")
	List<UserEntity> findByNameMode3(final Long age, final String name);

	List<UserEntity> findByNameAndAgeGreaterThanEqual(final String name, final Long age);
	
	List<UserEntity> findFirstTwoByName(final String name);
	
	List<UserEntity> findAllByName(final String name);
	
	List<UserEntity> findDistincAgeByNameIsNotNull();

	List<UserEntity> findAllByNameStartingWith(final String name);
}
