package com.tombutler.bestbets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tombutler.bestbets.models.LoginUser;
import com.tombutler.bestbets.models.User;
import com.tombutler.bestbets.services.UserService;

@Controller
public class HomeController {
	
//	@Autowired
//	private UserService userService;
	
	@GetMapping("/")
	public String index(
			@ModelAttribute("newUser") User user,
			@ModelAttribute("newLogin") LoginUser loginUser) {
		return "index.jsp";
	}

}
