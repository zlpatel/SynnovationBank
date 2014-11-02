package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Service.TechAccountAccessService;

@Service
@Transactional

public class TechAccountAccessServiceImpl implements TechAccountAccessService {

	@Autowired
	private PersonDAO personDAO;
	
	@Override
	public boolean setAccessFlag(String userID, String flag) {
	
		System.out.println("Inside flag modify DAO!");
		if(personDAO.updateAccessFlag(userID, flag))
			return true;
		else
			return false;
	}
	

}
