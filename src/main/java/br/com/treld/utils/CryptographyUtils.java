package br.com.treld.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("PMD")
public final class CryptographyUtils {
	
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();	
	
	public static String encrypt(String normalText){
		return encoder.encode(normalText);
	}
	
	public static boolean checkEquality(String normalText, String encodedText){
		return encoder.matches(normalText, encodedText);
	}
}
