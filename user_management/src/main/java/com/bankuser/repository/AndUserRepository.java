package com.bankuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankuser.model.entity.User;

@Repository
public interface AndUserRepository extends JpaRepository<User,Long> {

	public User getUserById(long id);
	
}
