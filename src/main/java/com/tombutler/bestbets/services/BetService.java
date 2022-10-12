package com.tombutler.bestbets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tombutler.bestbets.models.Bet;
import com.tombutler.bestbets.models.User;
import com.tombutler.bestbets.repositories.BetRepository;

@Service
public class BetService {

	@Autowired
	private BetRepository betRepo;
	
	public void validateWager(User user, Bet bet, BindingResult result) {
		if(user.getBalance() < bet.getWager()) {
			result.rejectValue("wager", "notEnough", "Not enough funds");
		}
	}
	
//	public int takeBets(User user, Bet bet) {
//		int userBalance = user.getBalance();
//		int wager = bet.getWager();
//		userBalance -= wager;
//		return userBalance;
//	}
	
	public List<Bet> allBets(){
		return betRepo.findAll();
	}
	
	public List<Bet> allBetsByUserId(User user){
		return betRepo.findAllByUser(user);
	}
	
	public Bet getOneBet(Long id) {
		return betRepo.findById(id).orElse(null);
	}
	
	public Bet createBet(Bet bet) {
		return betRepo.save(bet);
	}
		
	
	public Bet updateBet(Bet bet) {
		return betRepo.save(bet);
	}
	
	public void deleteBet(Long id) {
		betRepo.deleteById(id);
	}
}
