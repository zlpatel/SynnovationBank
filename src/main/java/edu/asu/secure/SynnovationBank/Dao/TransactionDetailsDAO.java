package edu.asu.secure.SynnovationBank.Dao;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;

public interface TransactionDetailsDAO {

	public List<TransactionDetails> fetchAccountTransactions(Long accountNo);
	public List<TransactionDetails> fetchAccountTransactions(Long accountNo, int rowCount);
}
