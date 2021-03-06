package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Service.AddExternalUserService;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.AddUserDao;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Dao.UsernamesDAO;
import edu.asu.secure.SynnovationBank.FormBean.ExternalUserFormBean;
import edu.asu.secure.SynnovationBank.Handler.PKICertificateHandler;
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
	@Autowired
	private UsernamesDAO usernamesDao;

	@Override

	public boolean addExternalUser(ExternalUserFormBean addexternaluserformbean) 

	{
		try
		{
		if(!usernamesDao.fetchUsername(addexternaluserformbean.getUsername()))
		{

			Person person = new Person();

			person.setFirstName(addexternaluserformbean.getFname());
			person.setLastName(addexternaluserformbean.getLname());
			person.setAddress(addexternaluserformbean.getAddress());
			person.setEmail(addexternaluserformbean.getEmail());
			person.setUserId(addexternaluserformbean.getUsername());
			person.setPassword(HashCode.getHashPassword(addexternaluserformbean.getPassword()));
			person.setDateOfBirth(addexternaluserformbean.getDateOfBirth());
			person.setRole(addexternaluserformbean.getRole());
			//	person.setRole("ROLE_CUST");
			person.setAllowAccessFlag(false);
			person.setAccountLockedFlag(false);
			person.setLoginAttempts(0);
			person.setPiiRequestFlag(false);

			Long uuid = UUID.randomUUID().getLeastSignificantBits();
			if(uuid < 0) {
				uuid = uuid * -1;
			}
			String ssn = uuid.toString().substring(0, 9);
			person.setSsn(ssn);

			Account account = new Account();
			account.setPerson(person);
			account.setRoutingNumber(12345);
			account.setAccountType("Checking");



			person.setAccount(account);

			if(personDao.insertUser(person))
			{
				if(usernamesDao.insertUsername(addexternaluserformbean.getUsername()))
				{
					PKICertificateHandler.genCert(person.getFirstName().concat(" ").concat(person.getLastName()), person.getUserId());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					PKICertificateHandler.sendCertificate(person.getUserId(), person.getEmail());

					return true;
				}

			}

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
