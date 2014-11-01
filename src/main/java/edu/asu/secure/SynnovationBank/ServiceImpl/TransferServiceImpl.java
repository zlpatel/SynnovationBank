package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Service.TransferService;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

	@Override
	public boolean performTransfer(String receiverID, String amount) {
		
		
/*
		
		// TODO Auto-generated method stub
		
		
		//ACCOUNT BALANCE MODIFICATION
		
		
		Account sender = null;
		
		//sender= DAO.getAccountInfo(myCurrentSessionUserId);  
	
		float sender_balance=sender.getBalance();
		float transferAmount=Float.parseFloat(amount);
		float new_balance=balance-transferAmount;
		if(new_balance<0)
		   return false;
		   
		sender.setBalance(new_balance);
		
		Account receiver = null;
		
		//receiver= DAO.getAccountInfo(receiverID);
		
		 float receiver_balance=receiver.getBalance();
		 receiver.setBalance(receiver_balance+transferAmount);
		 
		

		//DAO.updateAccount(sender);
		//DAO.updateAccount(receiver);
		
		
		
		
		//2 WAY TRANSACTION CREATION
		
		Transactions t=null;
		
		TransactionDetails td1=null;
		TransactionDetails td2=null;
		
		TransactionType ttype1=null;
		TransactionType ttype2=null;

		//ttype1 = DAO.returnTtypeObj(String transaction_name); // transaction_name would be credit
		//ttype1 = DAO.returnTtypeObj(String transaction_name); // transaction_name would be debit
		
		td1.setTransactionType(ttype1);
		td1.setAccount(receiver);
		
		td2.setTransactionType(ttype2);
		td2.setAccount(sender);
		
		
		
		
		Set<TransactionDetails> set = new HashSet<TransactionDetails>();
		set.add(td1);
		set.add(td2);
		
		t.setAmount(transferAmount);
		t.setTransactionDetails(set);
		
		
		//boolean status=DAO.updateTransaction(t);
		
		*
		*
		*/
		
		

		
	
		
		return true;
	}

}
