package com.xsis.batch197.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.batch197.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
	public UserModel findByUsername(String username);
	public UserModel findByEmail(String email);

	@Query(value="SELECT u FROM UserModel u WHERE u.username=:nama or u.email=:nama),'%')")
	public UserModel findByUserAndEmail(@Param("nama") String nama);
}
