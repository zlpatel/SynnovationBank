package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Handler.OTPGeneratorHandler;
import edu.asu.secure.SynnovationBank.Service.OTPService;
import edu.asu.secure.SynnovationBank.hash.HashCode;

@Service
@Transactional
public class OTPServiceImpl implements OTPService{
protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private PersonDAO personDao;

	@Override
	public boolean validateUser(String username, String email) {
		String otp=OTPGeneratorHandler.GenerateOTP();
//		if(personDao.updateOTP(username,email,otp)){
//			OTPGeneratorHandler.SendOTP(email, otp);
//			return true;
//		}
		return false;
	}

	@Override
	public boolean compareOTP(String otp) {
		
//		if(personDao.checkOTP(otp)){
//			return true;
//		}
		return false;
		
	}

	@Override
	public boolean updatePassword(String newpassword, String username) {
		String encodedPass=HashCode.getHashPassword(newpassword);
		if(personDao.updatePassword(username,encodedPass)){
			return true;
		}
		return false;
	}
	
}
