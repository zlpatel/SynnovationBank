package edu.asu.secure.SynnovationBank.Handler;

import java.util.Date;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.UserDetailsDao;

public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider{

//	@Autowired
//    private MyUserDetailsService userService;
	
	private UserDetailsDao userDetailsDao;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) 
          throws AuthenticationException {
 
	  try {
 
		Authentication auth = super.authenticate(authentication);
		//if reach here, means login success, else an exception will be thrown
		//reset the user_attempts
		System.out.println("Coming here");

		userDetailsDao.resetFailAttempts(authentication.getName());
		 
		return auth;
 
	  } catch (BadCredentialsException e) {	
 
		  System.out.println("coming here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+authentication.getName());
		//invalid login, update to user_attempts
		userDetailsDao.updateFailAttempts(authentication.getName());
		throw e;
 
	  } catch (LockedException e){
 
		//this user is locked!
		String error = "";
		Person person = 
                    userDetailsDao.getUserAttempts(authentication.getName());
 
               if(person!=null){
			Date lastAttempts = person.getLastLoginFailure();
			error = "User account is locked! <br><br>Username : " 
                           + authentication.getName() + "<br>Last Attempts : " + lastAttempts;
		}else{
			error = e.getMessage();
		}
 
	  throw new LockedException(error);
	}
	}
	
	public UserDetailsDao getUserDetailsDao() {
		return userDetailsDao;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}
}
