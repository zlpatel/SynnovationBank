package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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
import edu.asu.secure.SynnovationBank.Service.TransferService;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private TransactionTypeDAO transactionTypeDAO;
	@Autowired
	private TransactionsDAO transactionsDAO;
	@Autowired
	private NotificationsTypeDAO notificationsTypeDAO;
	
	@Autowired
	private NotificationsDAO notificationsDAO; 
	
	
	@Override
	public boolean performTransfer(String senderID, String receiverID, String amount) {
		
		if(Float.parseFloat(amount)<0)
		{
		System.out.println("***************************************************");
		System.out.println("CANNOT TRANSFER A NEGATIVE AMOUNT !");
		System.out.println("***************************************************");
		return false;
		}

		
		
		if(Float.parseFloat(amount)>500)
		{
						System.out.println("************************************************************************************************************");
						System.out.println("TRANSFER AMOUNT GREATER THAN $500--- CRITIAL TRANSACTION -- NEEDS APPROVAL -- NOTIFICATION SENT TO ADMIN  !");
						System.out.println("***********************************************************************************************************");
						
						
						// first create transaction
				
						
						System.out.println("*****************************************************************************************************");
						System.out.println("CREATING ONLY A TRANSACTION  ---- ACTUAL TRANSFER IS NOT PERFORMED  ---- SHOULD BE PERFORMED BY ROHIT!");
						System.out.println("*******************************************************************************************************");
						
						
						
						Person sender = personDAO.fetchUserById(senderID);
						Account a=(Account) sender.getAccount();
						float balance=a.getBalance();
						float debit=Float.parseFloat(amount);
						float new_balance=balance-debit;
						if(new_balance<0)
						{
							System.out.println("***************************************************");
							System.out.println("CANNOT TRANSFER FUNDS SINCE YOUR BALANCE FALLS BELOW 0!");
							System.out.println("***************************************************");
						
							return false;
						}
						//a.setBalance(new_balance);
						
						Person receiver = personDAO.fetchUserById(receiverID);
						Account b=(Account) receiver.getAccount();
						float balanceB=b.getBalance();
						float credit=Float.parseFloat(amount);
						float new_balanceB=balanceB+credit;
						//b.setBalance(new_balanceB);
						
						
						
						//2 WAY TRANSACTION CREATION
						
						
						System.out.println("***************************************************");
						System.out.println("CREATING 2 WAY TRANSACTION!");
						System.out.println("***************************************************");
						
						
						
						
						Transactions t=new Transactions();
						
						TransactionDetails td1=new TransactionDetails();
						TransactionDetails td2=new TransactionDetails();
						
						TransactionType ttype1=transactionTypeDAO.fetchTransactionType("debit");
						TransactionType ttype2=transactionTypeDAO.fetchTransactionType("credit");
						
						
						
						td1.setTransactionType(ttype1);
						td1.setAccount(a);
						
						
						td2.setTransactionType(ttype2);
						td2.setAccount(b);
						
					
						Set<TransactionDetails> set = new HashSet<TransactionDetails>();
						set.add(td1);
						set.add(td2);
						
						t.setAmount(debit);
						t.setTransactionDetails(set);
						t.setDate(Calendar.getInstance().getTime());
						
						long transactionID=transactionsDAO.insertTransaction(t);
						
						System.out.println("New 2 way credit and a debit transaction is populated with ID: "+transactionID);
						
				
						
						
						// generate notification
						
						
						//NotificationsDAOImpl impl = new NotificationsDAOImpl();
						//NotificationsTypeDAOImpl impl1 = new NotificationsTypeDAOImpl();
						
						System.out.println("Generating Critical Transaction notification request!\n");
						NotificationsType type = notificationsTypeDAO.fetchNotificationsType("CT");
						Transactions transactions = new Transactions();
						transactions.setTransactionId(transactionID);
						
						Notifications notification = new Notifications();
						notification.setEmpAdminFlag("A");
						notification.setResolvedFlag("N");
						notification.setNotificationsType(type);
						notification.setTransaction(transactions);
						notificationsDAO.insertNotification(senderID, notification);
						
						
						System.out.println("Successfully generated critical transaction notification request!\n");
						
						
						
						
						

						
						return false;
		}
		
		

		
		
		
		Person sender = personDAO.fetchUserById(senderID);
		Account a=(Account) sender.getAccount();
		float balance=a.getBalance();
		float debit=Float.parseFloat(amount);
		float new_balance=balance-debit;
		if(new_balance<0)
		{
			System.out.println("***************************************************");
			System.out.println("CANNOT TRANSFER FUNDS SINCE YOUR BALANCE FALLS BELOW 0!");
			System.out.println("***************************************************");
		
			return false;
		}
		a.setBalance(new_balance);
		
		Person receiver = personDAO.fetchUserById(receiverID);
		Account b=(Account) receiver.getAccount();
		float balanceB=b.getBalance();
		float credit=Float.parseFloat(amount);
		float new_balanceB=balanceB+credit;
		b.setBalance(new_balanceB);
		
		
		System.out.println("***************************************************");
		System.out.println("PERFORMING DEBIT FROM YOUR ACCOUNT AND CREDITING TO THE OTHER ACCOUNT!");
		System.out.println("***************************************************");
		
		
		accountDAO.updateAccountBalance(a.getAccountNumber(), a.getBalance());
		System.out.println("Debitted sender account table");
		
		
		accountDAO.updateAccountBalance(b.getAccountNumber(), b.getBalance());
		System.out.println("Creddited receiver account table");
		
		
		
		//2 WAY TRANSACTION CREATION
		
		
		System.out.println("***************************************************");
		System.out.println("CREATING 2 WAY TRANSACTION!");
		System.out.println("***************************************************");
		
		
		
		
		Transactions t=new Transactions();
		
		TransactionDetails td1=new TransactionDetails();
		TransactionDetails td2=new TransactionDetails();
		
		TransactionType ttype1=transactionTypeDAO.fetchTransactionType("debit");
		TransactionType ttype2=transactionTypeDAO.fetchTransactionType("credit");
		
		
		
		td1.setTransactionType(ttype1);
		td1.setAccount(a);
		
		
		td2.setTransactionType(ttype2);
		td2.setAccount(b);
		
	
		Set<TransactionDetails> set = new HashSet<TransactionDetails>();
		set.add(td1);
		set.add(td2);
		
		t.setAmount(debit);
		t.setTransactionDetails(set);
		t.setDate(Calendar.getInstance().getTime());
		
		long transactionID=transactionsDAO.insertTransaction(t);
		
		System.out.println("New 2 way credit and a debit transaction is populated with ID: "+transactionID);
		

		return true;
	}

}
