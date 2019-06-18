package com.xsis.batch197.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.batch197.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
	public UserModel findByUsername(String username);
}
