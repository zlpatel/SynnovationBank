package edu.asu.secure.SynnovationBank.DBUtilities;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.Role;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DaoImpl.PersonDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.RoleDAOImpl;

public class SeedData {

	static long roleId = -1;
	
	public static void insertRoles(){
		RoleDAOImpl roleDAOImpl = new RoleDAOImpl();
		
		TransactionType typeCredit = new TransactionType();
		typeCredit.setTransactionName("CREDIT");
		typeCredit.setDescription("Credited the account");
		
		TransactionType typeDebit = new TransactionType();
		typeDebit.setTransactionName("DEBIT");
		typeDebit.setDescription("Debited the account");
		
		TransactionType typeATM = new TransactionType();
		typeATM.setTransactionName("ATM");
		typeATM.setDescription("ATM Withdrawl");
		
		Set<TransactionType> tranTypeSet = new HashSet<TransactionType>();
		tranTypeSet.add(typeCredit);
		tranTypeSet.add(typeDebit);
		tranTypeSet.add(typeATM);
		
		Role roleCust = new Role();
		roleCust.setRoleName("customer");
		roleCust.setTransactionType(tranTypeSet);
		roleId = roleDAOImpl.insertRole(roleCust);
		
		/*Role roleMerchant = new Role();
		roleMerchant.setRoleName("merchant");
		roleMerchant.setTransactionType(tranTypeSet);
		roleDAOImpl.insertRole(roleMerchant);
		
		Role roleEmployee = new Role();
		roleEmployee.setRoleName("employee");
		roleDAOImpl.insertRole(roleEmployee);
		
		Role roleAdmin = new Role();
		roleAdmin.setRoleName("admin");
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
		
		Role role = new Role();
		role.setRoleId(roleId);
		role.setRoleName("customer");
		
		/*Account account = new Account();
		account.setAccountType("Checking");
		account.setBalance(0);
		account.setRoutingNumber(123);
		
		Set<Account> accountSet = new HashSet<Account>();
		accountSet.add(account);
		person.setAccount(accountSet);*/
		person.setRole(role);
		
		impl.insertUser(person);
	}
	
	
}
