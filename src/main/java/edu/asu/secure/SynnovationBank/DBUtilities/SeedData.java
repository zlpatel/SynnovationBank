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
	
	public static long insertRoles(String roleName){
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
		roleCust.setRoleName(roleName);
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
		return roleId;
	}
	
	public static void insertUser(){
		PersonDAOImpl impl = new PersonDAOImpl();
		
		Person person = new Person();
		person.setUserId("admin");
		person.setPassword("admin");
		person.setFirstName("admin");
		person.setLastName("admin");
		person.setDateOfBirth(Calendar.getInstance().getTime());
		person.setEmail("zlpatel@hotmail.com");
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("ROLE_ADMIN");
		
		/*Account account = new Account();
		account.setAccountType("Checking");
		account.setBalance(0);
		account.setRoutingNumber(123);
		
		Set<Account> accountSet = new HashSet<Account>();
		accountSet.add(account);
		person.setAccount(accountSet);*/
		person.setRole(role);
		
		impl.insertUser(person);
		
		
		person.setUserId("employee");
		person.setPassword("employee");
		person.setFirstName("employee");
		person.setLastName("employee");
		person.setDateOfBirth(Calendar.getInstance().getTime());
		person.setEmail("zlpatel@hotmail.com");
		
		role.setRoleId(2);
		role.setRoleName("ROLE_BNK_EMPL");
		
		person.setRole(role);
		
		impl.insertUser(person);
		
		
		person.setUserId("customer");
		person.setPassword("customer");
		person.setFirstName("customer");
		person.setLastName("customer");
		person.setDateOfBirth(Calendar.getInstance().getTime());
		person.setEmail("zlpatel@hotmail.com");
		
		role.setRoleId(3);
		role.setRoleName("ROLE_CUST");
		
		person.setRole(role);
		
		impl.insertUser(person);
		
		
		person.setUserId("merchant");
		person.setPassword("merchant");
		person.setFirstName("merchant");
		person.setLastName("merchant");
		person.setDateOfBirth(Calendar.getInstance().getTime());
		person.setEmail("zlpatel@hotmail.com");
		
		role.setRoleId(4);
		role.setRoleName("ROLE_MERC");
		
		person.setRole(role);
		
		impl.insertUser(person);
	}
	
	
}
