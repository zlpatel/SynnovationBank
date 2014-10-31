package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Service.AddExternalUserService;
import edu.asu.secure.SynnovationBank.Dao.AddUserDao;


@Service
@Transactional
public class AddExternalUserServiceImpl implements AddExternalUserService{
	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	private AddUserDao addUserDao;
	
	@Override
	public boolean addExternalUser(String fname) 
	{
		if(addUserDao.addUser(fname))
			return true;
		
		return false;
	}

}
