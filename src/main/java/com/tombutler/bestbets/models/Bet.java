package com.tombutler.bestbets.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="bets")
public class Bet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long gameId;
	
//	@NotEmpty(message="Enter your wager")
	@Min(value=1, message="Wager must be more than 1 point")
	private int wager;
	
	private String game;
	private String teamPicked;
	private String teamNotPicked;
	
	private boolean pickedHomeTeam = false;
	private boolean isComplete = false;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public Bet() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public int getWager() {
		return wager;
	}

	public void setWager(int wager) {
		this.wager = wager;
	}

	public boolean isPickedHomeTeam() {
		return pickedHomeTeam;
	}

	public void setPickedHomeTeam(boolean pickedHomeTeam) {
		this.pickedHomeTeam = pickedHomeTeam;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getTeamPicked() {
		return teamPicked;
	}

	public void setTeamPicked(String teamPicked) {
		this.teamPicked = teamPicked;
	}
	
	

//	public String getTeamNotPicked() {
//		return teamNotPicked;
//	}
//
//	public void setTeamNotPicked(String teamNotPicked) {
//		this.teamNotPicked = teamNotPicked;
//	}

	public String getTeamNotPicked() {
		return teamNotPicked;
	}

	public void setTeamNotPicked(String teamNotPicked) {
		this.teamNotPicked = teamNotPicked;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
//    public void getTeamNotPicked() {
//    	String str = getGame();
//    	String[] arr = null;
//    	arr = str.split(" ");
//    	String[] teamPickedArr = null;
//    	System.out.println(arr);
//    }
    
	
}
