package com.tombutler.bestbets.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tombutler.bestbets.models.LoginUser;
import com.tombutler.bestbets.models.User;
import com.tombutler.bestbets.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
//	Validate user
	public void validate(User newUser, BindingResult result) {
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "Mismatch", "Passwords do not match");
		}
		if(userRepo.findByEmail(newUser.getEmail()) != null) {
			result.rejectValue("email", "duplicate", "Email already taken");
		}
	}

	public User registerUser(User newUser) {
		String hashedPass = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPass);
		userRepo.save(newUser);
		return null;
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User findById(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	
//	public User findAll(Long id) {
//		return userRepo.findAll(id);
//	}
	
	public boolean authenticateUser(LoginUser newLogin, BindingResult result) {
		User user = userRepo.findByEmail(newLogin.getEmail());
		if(user == null) {
			result.rejectValue("email", "notFound", "Email not found");
			return false;
		}
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Mismatch", "Incorrect password");
			return false;
		}
		return true;
	}
}
