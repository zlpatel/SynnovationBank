package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.Person;

public interface PersonDAO {

	public boolean insertUser(Person person);
	public boolean updateUser(String userId, Person person);
	public boolean authenticateUser(String userId, String password);
	public Person fetchPersonById(String userId);
	public boolean updateOTP(String userId, String otp);
	public boolean insertAccount(Person person);
}
