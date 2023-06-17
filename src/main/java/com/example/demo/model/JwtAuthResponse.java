package com.example.demo.model;

public class JwtAuthResponse {
	   private String token;

	   public String getToken() {
		   return token;
	   }

	   public void setToken(String token) {
		   this.token = token;
	   }

	   public JwtAuthResponse(String token) {
		   this.token = token;
	   }

	   public JwtAuthResponse() {
		
	   }

	@Override
	public String toString() {
		return "JwtAuthResponse [token=" + token + "]";
	}
}
