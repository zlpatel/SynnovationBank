package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Service.CustomerInfoChangeService;

@Service
@Transactional
public class CustomerInfoChangeServiceImpl implements CustomerInfoChangeService {

	@Override
	public boolean changeCustomerInformation(String firstName, String lastName,String email) {
		// TODO Auto-generated method stub
		return true;
	}

}
