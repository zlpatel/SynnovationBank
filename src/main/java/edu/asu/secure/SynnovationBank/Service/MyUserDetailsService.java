package edu.asu.secure.SynnovationBank.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Dao.UserDaoTest;

public interface MyUserDetailsService {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException;

	public UserDaoTest getUserDao();

	public void setUserDao(UserDaoTest userDao);

}