package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Role;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Service.MyUserDetailsService;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService, MyUserDetailsService {
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private PersonDAO personDao;
 
	/* (non-Javadoc)
	 * @see edu.asu.secure.SynnovationBank.ServiceImpl.MyUserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
               throws UsernameNotFoundException {
 
		logger.debug("username is :"+username);
		edu.asu.secure.SynnovationBank.DTO.Person person = personDao.fetchPersonById(username);
		List<GrantedAuthority> authorities = buildUserAuthority(person.getRole());
 
		return buildUserForAuthentication(person, authorities);
 
 
	}
 
	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.Person
	private User buildUserForAuthentication(edu.asu.secure.SynnovationBank.DTO.Person person, 
		List<GrantedAuthority> authorities) {
		return new User(person.getUserId(), 
			person.getPassword(), true, 
                        true, true, true, authorities);
	}
 
	private List<GrantedAuthority> buildUserAuthority(Role userRole) {
 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
 
		// Build user's authorities
			setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
 
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
	}
 
	/* (non-Javadoc)
	 * @see edu.asu.secure.SynnovationBank.ServiceImpl.MyUserDetailsService#getPersonDao()
	 */
	@Override
	public PersonDAO getPersonDao() {
		return personDao;
	}
 
	/* (non-Javadoc)
	 * @see edu.asu.secure.SynnovationBank.ServiceImpl.MyUserDetailsService#setPersonDao(edu.asu.secure.SynnovationBank.Dao.PersonDAO)
	 */
	@Override
	public void setPersonDao(PersonDAO personDao) {
		this.personDao = personDao;
	}
 
}
