package edu.asu.secure.SynnovationBank.Dao;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;

public interface TransactionDetailsDAO {

	public long insertTransactionDetails(TransactionDetails transactionDetails);
	public boolean deleteTransactionDetailsById(Long transactionId);
	public List<TransactionDetails> fetchLastTenTransactions(Long accountNo); 
}
