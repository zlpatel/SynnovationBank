package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionsDAO;
import edu.asu.secure.SynnovationBank.Service.CreditService;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

	

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private TransactionTypeDAO transactionTypeDAO;
	@Autowired
	private TransactionsDAO transactionsDAO;
	
	@Override
	public boolean creditAmount(String userName,String amount) {
		
		//ACCOUNT BALANCE MODIFICATION
		
		Person sender = personDAO.fetchUserById(userName);
		Account a=(Account) sender.getAccount();
		float balance=a.getBalance();
		float credit=Float.parseFloat(amount);
		float new_balance=balance+credit;
		a.setBalance(new_balance);
		accountDAO.updateAccountBalance(a.getAccountNumber(), a.getBalance());
		System.out.println("Updated customer account table with new credit balance!");
		
		
		
		//TRANSACTION CREATION
		
		Transactions t=new Transactions();
		
		TransactionDetails td=new TransactionDetails();
		
		TransactionType ttype=transactionTypeDAO.fetchTransactionType("credit");

		
		
		
		td.setTransactionType(ttype);
		td.setAccount(a);
		
		
		
		
		
		Set<TransactionDetails> set = new HashSet<TransactionDetails>();
		set.add(td);
		
		t.setAmount(credit);
		t.setTransactionDetails(set);
		
		long transactionID=transactionsDAO.insertTransaction(t);
		
		System.out.println("New transaction populated with ID: "+transactionID);
		
		
		
		return true;
	}

}
