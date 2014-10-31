package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Dao.UserDaoTest;
import edu.asu.secure.SynnovationBank.Handler.OTPGeneratorHandler;
import edu.asu.secure.SynnovationBank.Service.OTPService;
import edu.asu.secure.SynnovationBank.hash.HashCode;

@Service
@Transactional
public class OTPServiceImpl implements OTPService{
protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private UserDaoTest userDao;

	@Override
	public boolean validateUser(String username, String email) {
		// TODO Auto-generated method stub
		String otp=OTPGeneratorHandler.GenerateOTP();
		if(userDao.updateOTP(username,email,otp)){
			OTPGeneratorHandler.SendOTP(email, otp);
			return true;
		}
		return false;
	}

	@Override
	public boolean compareOTP(String otp) {
		
		if(userDao.checkOTP(otp)){
			return true;
		}
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updatePassword(String newpassword, String username) {
		// TODO Auto-generated method stub
		
		String encodedPass=HashCode.getHashPassword(newpassword);
		if(userDao.updatePassword(encodedPass, username)){
			return true;
		}
		return false;
	}
	
}
