package com.tombutler.bestbets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.catalina.connector.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class BestBets1Application {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(BestBets1Application.class, args);
		//final String API_URL = "https://sportspage-feeds.p.rapidapi.com/games?status=scheduled&league=NFL";

		
		// "https://sportspage-feeds.p.rapidapi.com/games?status=scheduled&league=NFL"
		
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest.newBuilder()
//				.GET()
//				.header("accept", "application/json")
//				.uri(URI.create(API_URL))
//				.build();
//		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//		
//		System.out.println(response.body());
		
//		OkHttpClient client = new OkHttpClient();
//
//		Request request = new Request.Builder()
//			.url("https://sportspage-feeds.p.rapidapi.com/games?status=scheduled&league=NFL")
//			.get()
//			.addHeader("X-RapidAPI-Key", "e6f0a9f01dmshd25ffda5908db2ep1406dfjsnd4cc8a4f6f89")
//			.addHeader("X-RapidAPI-Host", "sportspage-feeds.p.rapidapi.com")
//			.build();
//
//		Response response = client.newCall(request).execute();
		
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://sportspage-feeds.p.rapidapi.com/games?status=scheduled&league=NFL"))
//				.header("X-RapidAPI-Key", "e6f0a9f01dmshd25ffda5908db2ep1406dfjsnd4cc8a4f6f89")
//				.header("X-RapidAPI-Host", "sportspage-feeds.p.rapidapi.com")
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println(response.body());
		
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://sportspage-feeds.p.rapidapi.com/games?status=scheduled&league=NFL"))
//				.header("X-RapidAPI-Key", "e6f0a9f01dmshd25ffda5908db2ep1406dfjsnd4cc8a4f6f89")
//				.header("X-RapidAPI-Host", "sportspage-feeds.p.rapidapi.com")
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println(response.body());
	}
	
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
	
	
	
	
	
			

}
