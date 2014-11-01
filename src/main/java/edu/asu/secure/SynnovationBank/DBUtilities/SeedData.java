package edu.asu.secure.SynnovationBank.DBUtilities;

import java.util.Calendar;

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DaoImpl.PersonDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.TransactionTypeDAOImpl;

public class SeedData {

	static long roleId = -1;
	
	public static void insertRoles(){
		/*RoleDAOImpl roleDAOImpl = new RoleDAOImpl();*/
		TransactionTypeDAOImpl trantypeDAOImpl = new TransactionTypeDAOImpl();
		
		long credit = -1, debit = -1, atm = 1;
		
		TransactionType typeCredit = new TransactionType();
		typeCredit.setTransactionName("CREDIT");
		typeCredit.setDescription("Credited the account");
		credit = trantypeDAOImpl.insertTransactionType(typeCredit);
		typeCredit.setTransactionTypeId(credit);
		
		TransactionType typeDebit = new TransactionType();
		typeDebit.setTransactionName("DEBIT");
		typeDebit.setDescription("Debited the account");
		debit = trantypeDAOImpl.insertTransactionType(typeDebit);
		typeDebit.setTransactionTypeId(debit);
		
		TransactionType typeATM = new TransactionType();
		typeATM.setTransactionName("ATM");
		typeATM.setDescription("ATM Withdrawl");
		atm = trantypeDAOImpl.insertTransactionType(typeATM);
		typeATM.setTransactionTypeId(atm);
		
		/*Role roleCust = new Role();
		roleCust.setRoleName("C");
		
		Role roleMerchant = new Role();
		roleMerchant.setRoleName("M");
		
		Role roleEmployee = new Role();
		roleEmployee.setRoleName("E");
		
		Role roleAdmin = new Role();
		roleAdmin.setRoleName("A");
		
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(roleCust);
		roleSet.add(roleMerchant);
		roleSet.add(roleEmployee);
		roleSet.add(roleAdmin);
		
		Set<TransactionType> tranTypeSet = new HashSet<TransactionType>();
		tranTypeSet.add(typeCredit);
		tranTypeSet.add(typeDebit);
		tranTypeSet.add(typeATM);
		
		roleCust.setTransactionType(tranTypeSet);
		roleDAOImpl.insertRole(roleCust);
		roleMerchant.setTransactionType(tranTypeSet);
		roleDAOImpl.insertRole(roleMerchant);
		roleEmployee.setTransactionType(tranTypeSet);
		roleDAOImpl.insertRole(roleEmployee);
		roleAdmin.setTransactionType(tranTypeSet);
		roleDAOImpl.insertRole(roleAdmin);*/		
		
	}
	
	public static void insertUser(){
		PersonDAOImpl impl = new PersonDAOImpl();
		
		Person person = new Person();
		person.setUserId("jeff");
		person.setPassword("jeff");
		person.setFirstName("Jeffrey");
		person.setLastName("Isaac");
		person.setDateOfBirth(Calendar.getInstance().getTime());
		person.setEmail("jeffrey.isaac@gmail.com");
		person.setRole("C");
		
		/*Role role = new Role();
		role.setRoleName("customer");
		
		Account account = new Account();
		account.setAccountType("Checking");
		account.setBalance(0);
		account.setRoutingNumber(123);
		
		Set<Account> accountSet = new HashSet<Account>();
		accountSet.add(account);
		person.setAccount(accountSet);
		person.setRole(role);*/
		
		impl.insertUser(person);
	}
	
	
}
