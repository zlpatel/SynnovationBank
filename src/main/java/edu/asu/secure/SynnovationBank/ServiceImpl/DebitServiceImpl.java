package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Service.DebitService;

@Service
@Transactional

public class DebitServiceImpl implements DebitService{

	@Override
	public boolean debitAmount(String amount) {
		// TODO Auto-generated method stub
		
/*
		
		// TODO Auto-generated method stub
		
		
		//ACCOUNT BALANCE MODIFICATION
		
		
		Account a = null;
		//a= DAO.getAccountInfo(userId);  --> returns person object then get account  Person p=fetchPerson(userID); a=p.getAccount();
		float balance=a.getBalance();
		float debit=Float.parseFloat(amount);
		float new_balance=balance-debit;
		if(new_balance<0)
		   return false;
		   
		a.setBalance(new_balance);
		
		//DAO.updateAccount(a);
		
		
		
		//TRANSACTION CREATION
		
		Transactions t=null;
		
		TransactionDetails td=null;
		
		TransactionType ttype=null;

		//ttype = DAO.returnTtypeObj(String transaction_name); // transaction_name would be like credit/debit
		
		td.setTransactionType(ttype);
		td.setAccount(a);
		
		
		
		
		
		Set<TransactionDetails> set = new HashSet<TransactionDetails>();
		set.add(td);
		
		t.setAmount(debit);
		t.setTransactionDetails(set);
		
		
		//boolean status=DAO.updateTransaction(t);
		
		*
		*
		*/
		
		
		
		
		
		
		
		return true;
	}
	

}
