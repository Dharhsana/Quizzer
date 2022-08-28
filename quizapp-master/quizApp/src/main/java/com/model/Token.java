package com.model;

public class Token {

	private String username;
	private String password;
	private String session_expire;
	private String role;
	private String token;
	
	public Token() {
		super();
	}

	
	public Token(String username, String password, String session_expire, String role, String token) {
		super();
		this.username = username;
		this.password = password;
		this.session_expire = session_expire;
		this.role = role;
		this.token = token;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSession_expire() {
		return session_expire;
	}

	public void setSession_expire(String session_expire) {
		this.session_expire = session_expire;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Token [username=" + username + ", password=" + password + ", session_expire=" + session_expire
				+ ", role=" + role + ", token=" + token + "]";
	}
	
	
	
}
