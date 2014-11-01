package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.Account;

public interface AccountDAO {

	public long insertAccount(Account account);
	public boolean updateAccount(Account account);
}
