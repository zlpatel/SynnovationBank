package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * Reference org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
 * 
 * @author zeel
 * 
 */
public class CustomUserDetailsService extends JdbcDaoImpl {

	@Override
	public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
		super.setUsersByUsernameQuery(usersByUsernameQueryString);
	}

	@Override
	public void setAuthoritiesByUsernameQuery(String queryString) {
		super.setAuthoritiesByUsernameQuery(queryString);
	}

	//override to pass get accountNonLocked  
	@Override
	public List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(super.getUsersByUsernameQuery(), new String[] { username },
				new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						String username = rs.getString("user_id");
						String password = rs.getString("password");
						boolean enabled = true;
						boolean accountNonExpired = true;
						boolean credentialsNonExpired = true;
						boolean accountNonLocked = !rs.getBoolean("account_locked_flag");
						String role=rs.getString("role");
						List<GrantedAuthority> authorities = buildUserAuthority(role);
						return new User(username, password, enabled, accountNonExpired, credentialsNonExpired,
								accountNonLocked, authorities);
					}

				});
	}
 
		private List<GrantedAuthority> buildUserAuthority(String userRole) {
	 
			Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
	 
			// Build user's authorities
				setAuths.add(new SimpleGrantedAuthority(userRole));
			List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
	 
			return Result;
		}
	//override to pass accountNonLocked
	@Override
	public UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();

		if (super.isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}

		return new User(returnUsername, userFromUserQuery.getPassword(), true,
				true, true,
				userFromUserQuery.isAccountNonLocked(), combinedAuthorities);
	}

}