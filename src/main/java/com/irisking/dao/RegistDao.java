package com.irisking.dao;

import org.springframework.data.repository.CrudRepository;

import com.irisking.requestbody.RegistRequest;

public interface RegistDao extends CrudRepository<RegistRequest, Long>{
    
}
