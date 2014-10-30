package edu.asu.secure.SynnovationBank.Service;

public interface OTPService {

	public boolean validateUser(String username, String email);

	public boolean compareOTP(String otp);

	boolean updatePassword(String newpassword, String username); 

}
