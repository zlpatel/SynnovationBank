package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.Transactions;

public interface TransactionsDAO {

	public long insertTransaction(Transactions transactions);
	
	public boolean updateTransactionCompleteFlag(Long transactionId, String completeFlag);
	
	public Transactions fetchTransactionById(Long transactionId);
	public long fetchCreditorAccountNo(Long transactionId);
	
	public boolean deleteTransactionById(Long transactionId);
}
