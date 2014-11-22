package edu.asu.secure.SynnovationBank.Dao;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Person;

public interface PersonDAO {

	public boolean insertUser(Person person);
	public boolean insertAccount(String userId, Account account);	

	public boolean updateOTP(String userId, String email, String otp);
	public boolean updateUserDetails(String userId, String fname, String mname, String lname, String email, String address);
	public boolean updateUserDetails(String userId, String fname, String mname, String lname, String email, String address, boolean accountLockedFlag);
	public boolean updatePassword(String userId, String password);
	public boolean updateAccessFlag(String userId, boolean accessFlag);
	public boolean updateFailedLoginAttempt(String userId);
	public boolean updatePIIRequestFlag(String userId, boolean piiRequestFlag);
	
	public boolean resetFailedLoginAttempt(String userId);
	
	public boolean authenticateOTP(String userId, String otp);
	public boolean authenticateUser(String userId, String password);
	
	public Person fetchUserById(String userId);
	public List<Person> fetchUserByRole(String rolename);
	
	public boolean deleteUser(String userId);
}
