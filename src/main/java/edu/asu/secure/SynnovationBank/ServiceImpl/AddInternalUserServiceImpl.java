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
import edu.asu.secure.SynnovationBank.Dao.UsernamesDAO;
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
	@Autowired
	private UsernamesDAO usernamesDao;

	@Override
	public boolean addInternalUser(InternalUserFormBean addinternaluserformbean) 
	{
		try
		{
			System.out.println("internal username = " + addinternaluserformbean.getUsername());

			if(!usernamesDao.fetchUsername(addinternaluserformbean.getUsername()))
			{
				Person person = new Person();

				person.setFirstName(addinternaluserformbean.getFname());
				person.setLastName(addinternaluserformbean.getLname());
				person.setAddress(addinternaluserformbean.getAddress());
				person.setEmail(addinternaluserformbean.getEmail());
				person.setUserId(addinternaluserformbean.getUsername());
				person.setPassword(HashCode.getHashPassword(addinternaluserformbean.getPassword()));

				person.setDateOfBirth(addinternaluserformbean.getDateOfBirth());
				//get role from form bean
				person.setRole("ROLE_BNK_EMPL");
				person.setAllowAccessFlag(false);
				person.setAccountLockedFlag(false);
				person.setLoginAttempts(0);
				person.setPiiRequestFlag(false);

				usernamesDao.insertUsername(addinternaluserformbean.getUsername());

				return personDao.insertUser(person);
			}
		}
		catch(Exception e)
		{
			System.out.println("some exception creating user " + e);
			return false;
		}

		return false;

	}


}


