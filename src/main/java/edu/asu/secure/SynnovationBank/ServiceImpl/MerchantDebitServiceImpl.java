package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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
import edu.asu.secure.SynnovationBank.Service.DebitService;
import edu.asu.secure.SynnovationBank.Service.MerchantDebitService;

@Service
@Transactional

public class MerchantDebitServiceImpl implements MerchantDebitService{

	
	

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private TransactionTypeDAO transactionTypeDAO;
	@Autowired
	private TransactionsDAO transactionsDAO;
	
			

	@Override
	public boolean debitAmount(String userName,String amount) {
	

		if(Float.parseFloat(amount)<0)
		{
		System.out.println("***************************************************");
		System.out.println("CANNOT DEBIT NEGATIVE AMOUNT !");
		System.out.println("***************************************************");
		return false;
		}
		
	//ACCOUNT BALANCE MODIFICATION
		
		Person sender = personDAO.fetchUserById(userName);
		Account a=(Account) sender.getAccount();
		float balance=a.getBalance();
		float debit=Float.parseFloat(amount);
		float new_balance=balance-debit;
		if(new_balance<0)
		{
			System.out.println("***************************************************");
			System.out.println("CANNOT DEBIT SINCE BALANCE FALLS BELOW 0!");
			System.out.println("***************************************************");
			return false;
		}
		a.setBalance(new_balance);
		accountDAO.updateAccountBalance(a.getAccountNumber(), a.getBalance());
		System.out.println("Updated customer account table with new debit balance:" +a.getBalance());
		
		
		
		//TRANSACTION CREATION
		
		
		System.out.println("***************************************************");
		System.out.println("CREATING A TRANSACTION!");
		System.out.println("***************************************************");
		
		
		Transactions t=new Transactions();
		
		TransactionDetails td=new TransactionDetails();
		
		TransactionType ttype=transactionTypeDAO.fetchTransactionType("debit");

		
		
		
		td.setTransactionType(ttype);
		td.setAccount(a);
		
		
		
		
		
		Set<TransactionDetails> set = new HashSet<TransactionDetails>();
		set.add(td);
		
		t.setAmount(debit);
		t.setTransactionDetails(set);
		t.setDate(Calendar.getInstance().getTime());
		
		long transactionID=transactionsDAO.insertTransaction(t);
		
		System.out.println("New debit transaction populated with ID: "+transactionID);
		

		
		
		
		
				
		return true;
	}
	

}
