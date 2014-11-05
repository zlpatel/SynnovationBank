package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionsDAO;
import edu.asu.secure.SynnovationBank.DaoImpl.NotificationsDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.NotificationsTypeDAOImpl;
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
	@Autowired
	private NotificationsDAO notificationsDAO;
	@Autowired
	private NotificationsTypeDAO notificationsTypeDAO;
	
	
	@Override
	public boolean creditAmount(String userName,String amount) {
		
		
		if(Float.parseFloat(amount)<0)
		{
		System.out.println("***************************************************");
		System.out.println("CANNOT CREDIT NEGATIVE AMOUNT !");
		System.out.println("***************************************************");
		return false;
		}
		
		
				
		
		System.out.println("***************************************************");
		System.out.println("UPDATING BALANCE!");
		System.out.println("***************************************************");
		
		
		
		//ACCOUNT BALANCE MODIFICATION
		System.out.println("USER "+userName);
		
		
		Person sender = personDAO.fetchUserById(userName);
		System.out.println("FETCHED USER "+sender.getFirstName()+" "+sender.getLastName());
		Account a=sender.getAccount();
		System.out.println("Current Available Balance: "+a.getBalance());
		float balance=a.getBalance();
		float credit=Float.parseFloat(amount);
		float new_balance=balance+credit;
		//a.setBalance(new_balance);
		accountDAO.updateAccountBalance(a.getAccountNumber(), new_balance);
		System.out.println("Updated customer account table with new credit balance:" + new_balance);
		
		
		
		
		//TRANSACTION CREATION
		
		System.out.println("***************************************************");
		System.out.println("CREATING A TRANSACTION!");
		System.out.println("***************************************************");
		
		Transactions t=new Transactions();
		
		TransactionDetails td=new TransactionDetails();
		
		TransactionType ttype=transactionTypeDAO.fetchTransactionType("DEPOSIT");

		
		
		
		td.setTransactionType(ttype);
		td.setAccount(a);
		
		
		
		
		
		Set<TransactionDetails> set = new HashSet<TransactionDetails>();
		set.add(td);
		
		t.setAmount(credit);
		t.setTransactionDetails(set);
		t.setDate(Calendar.getInstance().getTime());
		
		long transactionID=transactionsDAO.insertTransaction(t);
		
		System.out.println("New transaction populated with ID: "+transactionID);
		
		
		
		return true;
	}

}
