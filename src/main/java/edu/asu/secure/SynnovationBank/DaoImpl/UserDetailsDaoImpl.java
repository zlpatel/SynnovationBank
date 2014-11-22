package edu.asu.secure.SynnovationBank.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;



import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.UserDetailsDao;

@Repository
public class UserDetailsDaoImpl extends JdbcDaoSupport implements UserDetailsDao {

	private static final String SQL_USERS_UPDATE_LOCKED = "UPDATE PERSON SET account_locked_flag = ? WHERE user_id = ?";
	private static final String SQL_USERS_COUNT = "SELECT count(*) FROM PERSON WHERE user_id = ?";

	private static final String SQL_USER_ATTEMPTS_GET = "SELECT * FROM PERSON WHERE user_id = ?";
	private static final String SQL_USER_ATTEMPTS_INSERT = "INSERT INTO USER_ATTEMPTS (USERNAME, ATTEMPTS, LASTMODIFIED) VALUES(?,?,?)";

	private static final String SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE PERSON SET login_attempts = login_attempts + 1, lastlogin_failure = ? WHERE user_id = ?";
	private static final String SQL_USER_RESET_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE PERSON SET login_attempts = 1, lastlogin_failure = ? WHERE user_id = ?";
	private static final String SQL_USER_ATTEMPTS_RESET_ATTEMPTS = "UPDATE PERSON SET login_attempts = 0, lastlogin_failure = null, account_locked_flag = false WHERE user_id = ?";

	private static final int MAX_ATTEMPTS = 3;

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public void updateFailAttempts(String username) {

		Person user = getUserAttempts(username);
		if (user == null) {
			if (isUserExists(username)) {
				// if no record, insert a new
				getJdbcTemplate().update(SQL_USER_ATTEMPTS_INSERT, new Object[] { username, 1, new Date() });
			}
		} else {

			if (isUserExists(username)) {
				if(user.getLastLoginFailure()==null){
					getJdbcTemplate().update(SQL_USER_RESET_ATTEMPTS_UPDATE_ATTEMPTS, new Object[] { new Date(), username });
				}
				else{
					Calendar c = Calendar.getInstance();
					c.setTime(user.getLastLoginFailure());
					c.add(Calendar.DATE, 1);
					Calendar cal = Calendar.getInstance();
					Date currentDate = cal.getTime();
					
					if(currentDate.compareTo(c.getTime())>=0){
						getJdbcTemplate().update(SQL_USER_RESET_ATTEMPTS_UPDATE_ATTEMPTS, new Object[] { new Date(), username });
					}
					else if(currentDate.compareTo(c.getTime())<0){
						getJdbcTemplate().update(SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS, new Object[] { new Date(), username });
					}
						
				}
				
			}

			if (user.getLoginAttempts() + 1 >= MAX_ATTEMPTS) {
				// locked user
				getJdbcTemplate().update(SQL_USERS_UPDATE_LOCKED, new Object[] { true, username });
				// throw exception
				throw new LockedException("User Account is locked!");
			}

		}

	}

	@Override
	public Person getUserAttempts(String username) {

		try {

			Person userAttempts = getJdbcTemplate().queryForObject(SQL_USER_ATTEMPTS_GET,
					new Object[] { username }, new RowMapper<Person>() {
						public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

							Person user = new Person();
							user.setUserId(rs.getString("user_id"));
							user.setLoginAttempts(rs.getInt("login_attempts"));
							user.setLastLoginFailure(rs.getDate("lastlogin_failure"));

							return user;
						}

					});
			return userAttempts;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public void resetFailAttempts(String username) {

		getJdbcTemplate().update(SQL_USER_ATTEMPTS_RESET_ATTEMPTS, new Object[] { username });

	}

	private boolean isUserExists(String username) {

		boolean result = false;

		int count = getJdbcTemplate().queryForObject(SQL_USERS_COUNT, new Object[] { username }, Integer.class);
		if (count > 0) {
			result = true;
		}

		return result;
	}

}