package com.poc.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.model.Party;

@Repository
public interface PartyRepository extends CrudRepository<Party, Long> {
	
	Collection<Party> findAll();

}
