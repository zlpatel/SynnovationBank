package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.Account;


public interface AccountDAO {

	public boolean updateAccountBalance(Long accNo, float balance);
	
	public Account fetchAccountByNumber(Long accNo);
	public boolean fetchAllowAccessFlag(Long accNo);
}
