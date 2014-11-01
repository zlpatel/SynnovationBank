package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Service.CreditService;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

	

	

	@Override
	public boolean creditAmount(String amount) {
		// TODO Auto-generated method stub
		return true;
	}

}
