package com.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import com.model.Token;

public final class Authentication {

	
	private static String chars = "ABCDEFGHIJKLMNOPQRSTWXYZabcdefghijklmnopqrstwxyz0123456789";
	private static Random random = new Random();
	private static int random_int = 0;
	private static int upper_bound = chars.length()-1;
	private static Map<String,Token> tokens = new HashMap<String,Token>();
	private static String prefix_token = "Bearer ";
	private static Logger logger = Logger.getLogger(Authentication.class.getName());
	
	public static String TOKEN_EXPIRED = "TOKEN EXPIRED";
	
	public static Token createToken(String username,String role) {
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[8];
		byte[] hashedToken;
		Token tokenModel = new Token();
		
		StringBuilder token = new StringBuilder(prefix_token);
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			hashedToken = md.digest(username.getBytes(StandardCharsets.UTF_8));
			
			
			for(byte b:hashedToken) {
				token.append(b);
			}
			

		} catch (NoSuchAlgorithmException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		token.append(ts.getNanos());
		for(int i = 0;i<25;i++) {
			token.append(chars.charAt(random.nextInt(upper_bound)));
		}
		
		tokenModel.setUsername(username);
		tokenModel.setToken(token.toString());
		tokenModel.setRole(role);
		tokens.put(token.toString(), tokenModel);

		 return tokenModel;
	}
	
	public static boolean checkToken(String token) {
		
		System.out.println(token);
		System.out.println(tokens.get(token));
		if(tokens.get(token)!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean logout(Token token) {
		
		if(tokens.get(token.getToken().toString())!=null) {
			tokens.remove(token.getToken().toString());
			logger.info("USER LOG OUT "+token.getUsername());
			return true;
		}
		else {
			logger.info("USER IS NOT LOGGED IN " +token.getUsername());
			return false;
		}
	}
}
