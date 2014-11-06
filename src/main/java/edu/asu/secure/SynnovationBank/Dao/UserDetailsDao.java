package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.Person;


public interface UserDetailsDao {

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);
	
	Person getUserAttempts(String username);

}