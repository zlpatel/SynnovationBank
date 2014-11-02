package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Service.AddExternalUserService;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.AddUserDao;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.FormBean.ExternalUserFormBean;
import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.hash.HashCode;


@Service
@Transactional
public class AddExternalUserServiceImpl implements AddExternalUserService{
	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	private PersonDAO personDao; 
	@Autowired
	private AccountDAO accountDao;
	
	@Override
	public boolean addExternalUser(ExternalUserFormBean addexternaluserformbean) 
	{
		Person person = new Person();

		person.setFirstName(addexternaluserformbean.getFname());
		person.setLastName(addexternaluserformbean.getLname());
		person.setAddress(addexternaluserformbean.getAddress());
		person.setEmail(addexternaluserformbean.getEmail());
		person.setUserId(addexternaluserformbean.getUsername());
		person.setPassword(HashCode.getHashPassword(addexternaluserformbean.getPassword()));
		
		person.setDateOfBirth(new Date(1220227200));

		//Need to set role once Jeffrey updates
		//		person.setRole("Customer");
		
		Account account = new Account();
		
		account.setPerson(person);
		account.setBalance(0.0f);
		account.setRoutingNumber(12345);
		account.setAccountType("Checking");
		
		long accountNumber = accountDao.insertAccount(account);
		account.setAccountNumber(accountNumber);
		
		//add this account to person after jeffrey makes the change at his end.
		
		if(accountNumber > 0)
		{
			return true;
		}
		
		return false;
	}

}
