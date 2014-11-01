package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Service.AddInternalUserService;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.AddUserDao;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.FormBean.InternalUserFormBean;
import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.hash.HashCode;


@Service
@Transactional
public class AddInternalUserServiceImpl implements AddInternalUserService{
	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	private PersonDAO personDao; 
	@Autowired
	private AccountDAO accountDao;
	
	@Override
	public boolean addInternalUser(InternalUserFormBean addinternaluserformbean) 
	{
		Person person = new Person();

		person.setFirstName(addinternaluserformbean.getFname());
		person.setLastName(addinternaluserformbean.getLname());
		person.setAddress(addinternaluserformbean.getAddress());
		person.setEmail(addinternaluserformbean.getEmail());
		person.setUserId(addinternaluserformbean.getUsername());
		person.setPassword(HashCode.getHashPassword(addinternaluserformbean.getPassword()));
		
		person.setDateOfBirth(new Date(1220227200));

		//Need to set role once Jeffrey updates
		//		person.setRole("Customer");
		
		return true;
	}


}


