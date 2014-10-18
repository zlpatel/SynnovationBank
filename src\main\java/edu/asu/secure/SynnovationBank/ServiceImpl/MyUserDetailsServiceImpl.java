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

import edu.asu.secure.SynnovationBank.DTO.UserRole;
import edu.asu.secure.SynnovationBank.Dao.UserDaoTest;
import edu.asu.secure.SynnovationBank.Service.MyUserDetailsService;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService, MyUserDetailsService {
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private UserDaoTest userDao;
 
	/* (non-Javadoc)
	 * @see edu.asu.secure.SynnovationBank.ServiceImpl.MyUserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
               throws UsernameNotFoundException {
 
		logger.debug("username is :"+username);
		edu.asu.secure.SynnovationBank.DTO.User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
 
		return buildUserForAuthentication(user, authorities);
 
 
	}
 
	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(edu.asu.secure.SynnovationBank.DTO.User user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), 
			user.getPassword(), user.isEnabled(), 
                        true, true, true, authorities);
	}
 
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
 
		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
 
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
	}
 
	/* (non-Javadoc)
	 * @see edu.asu.secure.SynnovationBank.ServiceImpl.MyUserDetailsService#getUserDao()
	 */
	@Override
	public UserDaoTest getUserDao() {
		return userDao;
	}
 
	/* (non-Javadoc)
	 * @see edu.asu.secure.SynnovationBank.ServiceImpl.MyUserDetailsService#setUserDao(edu.asu.secure.SynnovationBank.Dao.UserDaoTest)
	 */
	@Override
	public void setUserDao(UserDaoTest userDao) {
		this.userDao = userDao;
	}
 
}
