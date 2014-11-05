package edu.asu.secure.SynnovationBank.Dao;


public interface AccountDAO {

	public boolean updateAccountBalance(Long accNo, float balance);
	
	public boolean fetchAllowAccessFlag(Long accNo);
}
