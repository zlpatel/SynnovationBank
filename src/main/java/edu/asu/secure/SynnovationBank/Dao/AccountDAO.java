package edu.asu.secure.SynnovationBank.Dao;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Notifications;

public interface AccountDAO {

	public long insertAccount(Account account);
	public boolean updateAccount(Account account);
	public List<Account> fetchUserAccounts();
	
}
