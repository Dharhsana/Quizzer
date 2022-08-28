package com.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

public class Utility {

	private static String defualt_key;
	private static String hash_key;
	private static String pattern_date = "yyyy-MM-dd";
	private static String pattern_date_time = "yyyy.MM.dd G 'at' HH:mm:ss z";
	private static String chars = "ABCDEFGHIJKLMNOPQRSTWXYZabcdefghijklmnopqrstwxyz";
	private static Random random = new Random();
	private static int random_int = 0;
	private static int upper_bound = chars.length()-1;

	private static Logger logger = Logger.getLogger(Utility.class.getName());
	
	
	public static String getUEID(String key) {
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return key+"_"+ts.getNanos()+chars.charAt(random.nextInt(upper_bound))+chars.charAt(random.nextInt(upper_bound))+chars.charAt(random.nextInt(upper_bound));
	}
	
	public static String getPassword(String passwordToHash) {
		
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[8];
		byte[] hashedPassword;
		StringBuilder sb = new StringBuilder();
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			hashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			
			
			for(byte b:hashedPassword) {
				sb.append(b);
			}
			

		} catch (NoSuchAlgorithmException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		return sb.toString();

	}
	
	public static Date setDate(String date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern_date);
		Date db_date = new Date();
		try {
			db_date = sdf.parse(date);
		} catch (ParseException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		return db_date;
	}
	
	public static String getDate(Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern_date);
		String db_date = "";
		db_date = sdf.format(date);
		
		return db_date;
	}
	
	public static Date setDateTime(String date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern_date_time);
		Date db_date = new Date();
		try {
			db_date = sdf.parse(date);
		} catch (ParseException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		return db_date;
	}
	
	public static String getDateTime(Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern_date_time);
		String db_date = "";
		db_date = sdf.format(date);
		
		return db_date;
	}
	
	public static String getOtp() {
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String otp = "";
		otp = ""+ts.getNanos()+chars.charAt(random.nextInt(upper_bound))+chars.charAt(random.nextInt(upper_bound))+chars.charAt(random.nextInt(upper_bound));
		return otp;
	}
	
}
