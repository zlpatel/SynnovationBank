package edu.asu.secure.SynnovationBank.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.asu.secure.SynnovationBank.Dao.PersonDAO;

public interface MyUserDetailsService {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException;

	PersonDAO getPersonDao();

	void setPersonDao(PersonDAO personDao);

}