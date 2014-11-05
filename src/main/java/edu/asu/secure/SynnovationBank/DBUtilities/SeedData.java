package edu.asu.secure.SynnovationBank.DBUtilities;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionTypeDAO;
import edu.asu.secure.SynnovationBank.hash.HashCode;

public class SeedData {
	
	@Autowired
	private static TransactionTypeDAO transactionTypedao;
	
	@Autowired
	private static PersonDAO personDAO;
	
	@Autowired
	private static NotificationsTypeDAO notifDAO;
	
	public static void insertTransactionTypes(){
		
		TransactionType typeCredit = new TransactionType();
		typeCredit.setTransactionName("CREDIT");
		typeCredit.setDescription("Credited the account");
		transactionTypedao.insertTransactionType(typeCredit);
		
		TransactionType typeDebit = new TransactionType();
		typeDebit.setTransactionName("DEBIT");
		typeDebit.setDescription("Debited the account");
		transactionTypedao.insertTransactionType(typeDebit);
		
		TransactionType typeATM = new TransactionType();
		typeATM.setTransactionName("ATM");
		typeATM.setDescription("ATM Withdrawl");
		transactionTypedao.insertTransactionType(typeATM);
		
		TransactionType typeDeposit = new TransactionType();
		typeDeposit.setTransactionName("DEPOSIT");
		typeDeposit.setDescription("Deposited by self");
		transactionTypedao.insertTransactionType(typeDeposit);
		
	}
	
	public static void insertNotificationTypes(){
		
		NotificationsType criticalTran = new NotificationsType();
		criticalTran.setNotificationType("CT");
		criticalTran.setDescription("Critical Transaction");
		notifDAO.insertNotificationType(criticalTran);
		
		NotificationsType techAccess = new NotificationsType();
		techAccess.setNotificationType("TAA");
		techAccess.setDescription("Technical Account Access");
		notifDAO.insertNotificationType(techAccess);
		
	}
	
	public static void insertUser(){
		
		Person customer1 = new Person();
		customer1.setUserId("cust1");
		customer1.setPassword(HashCode.getHashPassword("cust1"));
		customer1.setFirstName("Jeffrey");
		customer1.setMiddleName("Joseph");
		customer1.setLastName("Isaac");
		customer1.setDateOfBirth(Calendar.getInstance().getTime());
		customer1.setEmail("jeffrey.isaac@gmail.com");
		customer1.setAddress("1265 E University");
		customer1.setAllowAccessFlag("N");
		customer1.setRole("ROLE_CUST");
		
		Account cust1account = new Account();
		cust1account.setAccountType("Checking");
		cust1account.setBalance(0);
		cust1account.setRoutingNumber(123);
		cust1account.setPerson(customer1);
		customer1.setAccount(cust1account);		
		personDAO.insertUser(customer1);
		
		Person customer2 = new Person();
		customer2.setUserId("cust2");
		customer2.setPassword(HashCode.getHashPassword("cust2"));
		customer2.setFirstName("Zeel");
		customer2.setLastName("Patel");
		customer2.setDateOfBirth(Calendar.getInstance().getTime());
		customer2.setEmail("zeel.patel@gmail.com");
		customer2.setAddress("1265 E University");
		customer2.setAllowAccessFlag("Y");
		customer2.setRole("ROLE_CUST");
		
		Account cust2account = new Account();
		cust2account.setAccountType("Checking");
		cust2account.setBalance(0);
		cust2account.setRoutingNumber(123);
		cust2account.setPerson(customer2);
		customer2.setAccount(cust2account);		
		personDAO.insertUser(customer2);
		
		Person merchant1 = new Person();
		merchant1.setUserId("merc1");
		merchant1.setPassword(HashCode.getHashPassword("merc1"));
		merchant1.setFirstName("Rohit");
		merchant1.setLastName("Kharat");
		merchant1.setDateOfBirth(Calendar.getInstance().getTime());
		merchant1.setEmail("rohit.kharat@gmail.com");
		merchant1.setAddress("1265 E University");
		merchant1.setAllowAccessFlag("Y");
		merchant1.setRole("ROLE_MERC");
		
		Account merc1account = new Account();
		merc1account.setAccountType("Checking");
		merc1account.setBalance(0);
		merc1account.setRoutingNumber(123);
		merc1account.setPerson(merchant1);
		merchant1.setAccount(merc1account);		
		personDAO.insertUser(merchant1);
		
		Person merchant2 = new Person();
		merchant2.setUserId("merc2");
		merchant2.setPassword(HashCode.getHashPassword("merc2"));
		merchant2.setFirstName("Pratik");
		merchant2.setLastName("Kasat");
		merchant2.setDateOfBirth(Calendar.getInstance().getTime());
		merchant2.setEmail("pratit.kasat@gmail.com");
		merchant2.setAddress("1265 E University");
		merchant2.setAllowAccessFlag("N");
		merchant2.setRole("ROLE_MERC");
		
		Account merc2account = new Account();
		merc2account.setAccountType("Checking");
		merc2account.setBalance(0);
		merc2account.setRoutingNumber(123);
		merc2account.setPerson(merchant2);
		merchant2.setAccount(merc2account);		
		personDAO.insertUser(merchant2);
		
		Person employee = new Person();
		employee.setUserId("emp");
		employee.setPassword(HashCode.getHashPassword("emp"));
		employee.setFirstName("Vignesh");
		employee.setLastName("Narayanan");
		employee.setDateOfBirth(Calendar.getInstance().getTime());
		employee.setEmail("vignesh.narayanan@gmail.com");
		employee.setAddress("1265 E University");
		employee.setAllowAccessFlag("Y");
		employee.setRole("ROLE_BNK_EMPL");		
		personDAO.insertUser(employee);
		
		Person admin = new Person();
		admin.setUserId("admin");
		admin.setPassword(HashCode.getHashPassword("admin"));
		admin.setFirstName("Meghna");
		admin.setLastName("P");
		admin.setDateOfBirth(Calendar.getInstance().getTime());
		admin.setEmail("meghna.p@gmail.com");
		admin.setAddress("1265 E University");
		admin.setAllowAccessFlag("Y");
		admin.setRole("ROLE_ADMIN");	
		personDAO.insertUser(admin);
	}
	
}
