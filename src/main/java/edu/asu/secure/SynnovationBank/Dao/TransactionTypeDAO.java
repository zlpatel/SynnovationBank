package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.TransactionType;

public interface TransactionTypeDAO {

	public long insertTransactionType(TransactionType transactionType);
	
	public TransactionType fetchTransactionType(String transactionName);
}
