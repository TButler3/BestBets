package com.tombutler.bestbets.controllers;


import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.el.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tombutler.bestbets.BestBets1Application;
import com.tombutler.bestbets.models.Bet;
import com.tombutler.bestbets.models.LoginUser;
import com.tombutler.bestbets.models.User;
import com.tombutler.bestbets.services.BetService;
import com.tombutler.bestbets.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BetService betService;
	
//	private String baseURL = "https://swapi.dev/api";
//	
//	@Autowired
//	RestTemplate restTemplate;
	
	@Autowired
	BestBets1Application api;
	
	@GetMapping("/")
	public String index(
			@ModelAttribute("newUser") User user,
			@ModelAttribute("newLogin") LoginUser loginUser) {
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("newUser") User user,
			BindingResult result, HttpSession session,
			@ModelAttribute("newLogin") LoginUser loginUser) {
		userService.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		userService.registerUser(user);
		session.setAttribute("loggedInUser", user);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/login")
	public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser loginUser,
			BindingResult result, HttpSession session,
			@ModelAttribute("newUser") User user) {
		userService.authenticateUser(loginUser, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		User loggedInUser = userService.findByEmail(loginUser.getEmail());
		session.setAttribute("loggedInUser", loggedInUser);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		if(session.getAttribute("loggedInUser")!=null) {
			return "dashboard.jsp";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/games")
	public String games(Model model, HttpSession session) throws UnirestException, IOException, InterruptedException, ParseException {
		
		String apiUrl = "https://sportspage-feeds.p.rapidapi.com/games?status=scheduled&league=NFL&date=2022-10-16";
		
		HttpResponse<JsonNode> jsonResponse = Unirest.get(apiUrl)
			.header("X-RapidAPI-Key", "e6f0a9f01dmshd25ffda5908db2ep1406dfjsnd4cc8a4f6f89")
			.header("X-RapidAPI-Host", "sportspage-feeds.p.rapidapi.com")
			.asJson();
		JSONObject obj = jsonResponse.getBody().getObject();
		
		JSONArray jArray = obj.getJSONArray("results");
		ArrayList<JSONObject> jResults = new ArrayList<JSONObject>();
		
		for(int i=0; i<jArray.length(); i++) {
			jResults.add(jArray.getJSONObject(i));
		}
		model.addAttribute("jResults", jResults);
		System.out.println(obj.toString());
		
		if(session.getAttribute("loggedInUser")!=null) {
			return "games.jsp";
		} else {
			return "redirect:/";
		}
	}
			
	
	@GetMapping("/bet/{gameId}")
	public String betOnGame(@PathVariable int gameId, Model model, @ModelAttribute("newBet") Bet bet, HttpSession session) {
		
		String apiUrl = "https://sportspage-feeds.p.rapidapi.com/gameById?gameId=" + gameId;
		
		HttpResponse<JsonNode> jsonResponse = null;
		try {
			jsonResponse = Unirest.get(apiUrl)
					.header("X-RapidAPI-Key", "e6f0a9f01dmshd25ffda5908db2ep1406dfjsnd4cc8a4f6f89")
					.header("X-RapidAPI-Host", "sportspage-feeds.p.rapidapi.com")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			JSONObject obj = jsonResponse.getBody().getObject();
			
			JSONArray jArray = obj.getJSONArray("results");
		
			ArrayList<JSONObject> jResults = new ArrayList<JSONObject>();
			
			for(int i=0; i<jArray.length(); i++) {
				jResults.add(jArray.getJSONObject(i));
			}
			model.addAttribute("jResults", jResults);
			System.out.println(obj.toString());
		
		if(session.getAttribute("loggedInUser")!=null) {
			return "betDetails.jsp";
		} else {
			return "redirect:/";
		}
		
	}
	
	@PostMapping("bet/create")
	public String createBet(@Valid @ModelAttribute("newBet") Bet bet, BindingResult result) {
		if(result.hasErrors()) {
			return "betDetails.jsp";
		} else {
			betService.createBet(bet);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/bets/{id}")
	public String seeBets(@PathVariable Long id, Model model, HttpSession session) {
		if(session.getAttribute("loggedInUser")!=null) {
			User userId = userService.findById(id);
			List<Bet> myBets = betService.allBetsByUserId(userId);
			model.addAttribute("myBets", myBets);
			return "userBets.jsp";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/bet/edit/{id}")
	public String editBet(@PathVariable Long id,
			HttpSession session,
			@ModelAttribute("editBet") Bet bet,
			Model model) {
		if(session.getAttribute("loggedInUser")!=null) {
			Bet myBet = betService.getOneBet(id);
			model.addAttribute("bet", myBet);
			return "editBet.jsp";
		} else {
			return "redirect:/";
		}
	}
	
	@PutMapping("/bet/{id}")
	public String updateBet(@Valid @ModelAttribute("editBet") Bet bet,
			BindingResult result) {
		if(result.hasErrors()) {
			return "editBet.jsp";
		} else {
			betService.updateBet(bet);
			return "redirect:/dashboard";
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBet(@PathVariable Long id,
			HttpSession session) {
		if(session.getAttribute("loggedInUser")!=null) {
			betService.deleteBet(id);
			return "redirect:/dashboard";
		} else {
			return "redirect:/";
		}
	}

}
