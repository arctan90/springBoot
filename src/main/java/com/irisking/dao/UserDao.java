package com.irisking.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.irisking.orm.UserOrm;

public interface UserDao extends CrudRepository<UserOrm, Long>{
	public UserOrm findById(Long id);
	
	public UserOrm findByName(String name);
//	@Query("FROM User u WHERE u.name=:name")
//	public UserOrm findUserByName(@Param("name") String name);
	
}
