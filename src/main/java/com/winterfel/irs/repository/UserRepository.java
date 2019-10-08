package com.winterfel.irs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winterfel.irs.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,String>{
	
	
}
