package edu.asu.secure.SynnovationBank.Dao;

import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.domain.DbUser;

public interface UserDao {

	/**
	 * Simulates retrieval of data from a database.
	 */
	public DbUser searchDatabase(String username);

}