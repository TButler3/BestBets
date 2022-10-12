package com.tombutler.bestbets.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Username is required!")
	@Size(min=3, max=28, message="Username must be between 3 and 28 characters")
	private String username;
	
	@NotEmpty(message="Email is required!")
	@Email(message="Not a valid email")
	private String email;
	
	@NotEmpty(message="Password required!")
	@Size(min=8, max=128, message="Password must be between 8 and 128 characters")
	private String password;
	
	@Transient
	@NotEmpty(message="Please confirm password")
	private String confirmPassword;
	
	private int balance = 5;
	
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Bet> bet;
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	

	public List<Bet> getBet() {
		return bet;
	}

	public void setBet(List<Bet> bet) {
		this.bet = bet;
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
    
//    public void takeBet(Bet bet) {
//		this.balance -= bet.getWager();
//	}
    
    public int takeBet(Bet bet) {
		int userBalance = balance;
		userBalance -= bet.getWager();
		return userBalance;
	}
	
	
}
