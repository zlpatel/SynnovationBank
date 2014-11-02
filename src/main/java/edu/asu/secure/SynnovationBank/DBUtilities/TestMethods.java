package edu.asu.secure.SynnovationBank.DBUtilities;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.ReportedIssues;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.DaoImpl.AccountDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.NotificationsDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.NotificationsTypeDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.PersonDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.ReportedIssuesDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.TransactionDetailsDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.TransactionTypeDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.TransactionsDAOImpl;

public class TestMethods {
	
	public static void updateOTP(){
		PersonDAOImpl impl = new PersonDAOImpl();		
		impl.updateOTP("cust1", "onetime");
	}
	
	public static void authenticateOTP(){
		PersonDAOImpl impl = new PersonDAOImpl();		
		boolean ret = impl.authenticateOTP("cust1", "jeffrey.isaac@gmail.com", "onetime");
		System.out.println("OTP Valid = "+ret);
	}
	
	public static void fetchPerson(){
		PersonDAOImpl impl = new PersonDAOImpl();
		List<Person> list = impl.fetchUserByRole("ROLE_CUST");
		Iterator<Person> itr = list.iterator();
		while(itr.hasNext())
			System.out.println(itr.next().getFirstName());
	}
	
	public static void updateAccBalance(){
		AccountDAOImpl impl = new AccountDAOImpl();
		impl.updateAccountBalance(1L, 100);
	}
	
	public static void fetchAllowAccessFlag(){
		AccountDAOImpl impl = new AccountDAOImpl();
		boolean ret = impl.fetchAllowAccessFlag(2L);
		System.out.println("Allow access = "+ret);
	}
	
	public static void insertTransaction(){
		TransactionTypeDAOImpl ttimpl = new TransactionTypeDAOImpl();
		TransactionsDAOImpl tnimpl = new TransactionsDAOImpl();
		
		Transactions transactions = new Transactions();
		transactions.setAmount(100);
		transactions.setDate(Calendar.getInstance().getTime());
		transactions.setCompleteFlag("Y");
		
		TransactionType credit = ttimpl.fetchTransactionType("CREDIT");
		TransactionType debit = ttimpl.fetchTransactionType("DEBIT");
		
		TransactionDetails cre = new TransactionDetails();
		cre.setTransactionType(credit);
		Account receiver = new Account();
		receiver.setAccountNumber(1);
		cre.setAccount(receiver);
		cre.setTransactions(transactions);
		
		TransactionDetails deb = new TransactionDetails();
		deb.setTransactionType(debit);
		Account sender = new Account();
		sender.setAccountNumber(2);
		deb.setAccount(sender);
		deb.setTransactions(transactions);
		
		Set<TransactionDetails> set = new HashSet<TransactionDetails>();
		set.add(deb);
		set.add(cre);
		transactions.setTransactionDetails(set);
		tnimpl.insertTransaction(transactions);
	}
	
	public static void deleteTransaction(){
		TransactionsDAOImpl tnimpl = new TransactionsDAOImpl();
		tnimpl.deleteTransactionById(3L);
	}
	
	public static void fetchAccountTransactions(){
		TransactionDetailsDAOImpl impl = new TransactionDetailsDAOImpl();
		List<TransactionDetails> list = impl.fetchAccountTransactions(2L,1);
		Iterator<TransactionDetails> itr = list.iterator();
		while(itr.hasNext()){
			TransactionDetails tran = itr.next();
			System.out.println("TRANSACTION");
			System.out.println("-----------");
			System.out.println(tran.getTransactions().getDate()+"\t"+tran.getTransactionType().getTransactionName()+"\t"+
					tran.getTransactions().getAmount()+"\t"+tran.getAccount().getBalance());
		}
		
	}
	
	public static void insertIssue(){
		ReportedIssuesDAOImpl impl = new ReportedIssuesDAOImpl();
		ReportedIssues issue = new ReportedIssues();
		issue.setDescription("Unable to access account");
		issue.setResolvedFlag("N");
		impl.insertIssue("cust1", issue);
	}
	
	public static void updateIssueResolved(){
		ReportedIssuesDAOImpl impl = new ReportedIssuesDAOImpl();
		impl.updateIssueResolved(1L);
	}
	
	public static void insertNotifications(){
		NotificationsDAOImpl impl = new NotificationsDAOImpl();
		NotificationsTypeDAOImpl impl1 = new NotificationsTypeDAOImpl();
		
		NotificationsType type = impl1.fetchNotificationsType("TAA");
		Transactions transactions = new Transactions();
		transactions.setTransactionId(1L);
		
		Notifications notification = new Notifications();
		notification.setEmpAdminFlag("E");
		notification.setResolvedFlag("N");
		notification.setNotificationsType(type);
		notification.setTransaction(transactions);
		impl.insertNotification("cust1", notification);
	}
	
}
