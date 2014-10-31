package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.User;

public interface UserDaoTest {

	User findByUserName(String username);

	boolean updateOTP(String username, String email, String otp);

	boolean checkOTP(String otp);

	boolean updatePassword(String newpassword, String username);
}
