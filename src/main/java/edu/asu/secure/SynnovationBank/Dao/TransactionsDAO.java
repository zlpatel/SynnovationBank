package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.Transactions;

public interface TransactionsDAO {

	public long insertTransaction(Transactions transactions);
	
	public boolean deleteTransactionById(Long transactionId);
}
