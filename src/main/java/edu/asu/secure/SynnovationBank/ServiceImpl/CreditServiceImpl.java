package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.Service.CreditService;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

	

	

	@Override
	public boolean creditAmount(String amount) {
		/*
		
		// TODO Auto-generated method stub
		
		
		//ACCOUNT BALANCE MODIFICATION
		
		
		Account a = null;
		//a= DAO.getAccountInfo(userId);
		float balance=a.getBalance();
		float credit=Float.parseFloat(amount);
		float new_balance=balance+credit;
		a.setBalance(new_balance);
		
		//DAO.updateAccount(a);
		
		
		
		//TRANSACTION CREATION
		
		Transactions t=null;
		
		TransactionDetails td=null;
		
		TransactionType ttype=null;

		//ttype = DAO.returnTtypeObj(String transaction_name);
		
		td.setTransactionType(ttype);
		td.setAccount(a);
		
		
		
		
		
		Set<TransactionDetails> set = new HashSet<TransactionDetails>();
		set.add(td);
		
		t.setAmount(credit);
		t.setTransactionDetails(set);
		
		
		//boolean status=DAO.updateTransaction(t);
		
		*
		*
		*/
		return true;
	}

}