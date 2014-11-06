package edu.asu.secure.SynnovationBank.Service;

public interface OTPService {

	public boolean validateUser(String username, String email);

	public boolean compareOTP(String userId, String otp); 

	public boolean updatePassword(String newpassword, String retypepassword, String username);

}
