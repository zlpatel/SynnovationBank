package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Service.CustomerInfoChangeService;

@Service
@Transactional
public class CustomerInfoChangeServiceImpl implements CustomerInfoChangeService {


	@Autowired
	private PersonDAO personDAO;
	
	@Override
	public boolean changeCustomerInformation(String userID,String firstName, String lastName,String email) {
		// TODO Auto-generated method stub
		
		System.out.println("Hello!");
		boolean status=personDAO.updateUserDetails(userID, firstName, " ", lastName, email, " ");
		System.out.println("Status is: "+status);
		return true;
	}

}
