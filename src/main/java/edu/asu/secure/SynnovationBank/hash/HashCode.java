package edu.asu.secure.SynnovationBank.hash;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashCode {

	public static String getHashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);
		return hashedPassword;
	}

}
