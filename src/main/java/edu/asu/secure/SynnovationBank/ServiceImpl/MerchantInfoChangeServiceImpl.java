package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Service.MerchantInfoChangeService;

@Service
@Transactional
public class MerchantInfoChangeServiceImpl implements MerchantInfoChangeService {


	@Autowired
	private PersonDAO personDAO;
	
	@Override
	public boolean changeCustomerInformation(String userID,String firstName, String middleName, String lastName,String address, String email) {
		// TODO Auto-generated method stub
		
		System.out.println("Hello!");
		
		boolean status=personDAO.updateUserDetails(userID, firstName, middleName, lastName, email, address);
		System.out.println("Status is: "+status);
		return true;
	}

}
