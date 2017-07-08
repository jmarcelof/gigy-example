package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByUsername(String username);
}
