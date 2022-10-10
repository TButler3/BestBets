package com.tombutler.bestbets.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tombutler.bestbets.models.Bet;
import com.tombutler.bestbets.models.User;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {
	
	List<Bet> findAll();
	List<Bet> findAllByUser(User user);

}
