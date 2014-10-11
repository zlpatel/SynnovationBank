package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.Dao.UserDao;
import edu.asu.secure.SynnovationBank.domain.DbUser;

/**
 * A custom DAO for accessing data from the database.
 *
 */
@Repository
public class UserDaoImpl implements UserDao  {

	protected static Logger logger = Logger.getLogger("dao");
	
	/**
	 * Simulates retrieval of data from a database.
	 */
	public DbUser searchDatabase(String username) {
		// Retrieve all users from the database
		List<DbUser> users = internalDatabase();
		
		// Search user based on the parameters
		for(DbUser dbUser:users) {
			if ( dbUser.getUsername().equals(username)  == true ) {
				logger.debug("User found");
				// return matching user
				return dbUser;
			}
		}
		
		logger.error("User does not exist!");
		throw new RuntimeException("User does not exist!");
	}

	/**
	 * Our fake database. Here we populate an ArrayList with a dummy list of users.
	 */
	private List<DbUser> internalDatabase() {
		// Dummy database
		
		// Create a dummy array list
		List<DbUser> users = new ArrayList<DbUser>();
		DbUser user = null;
		
		// Create a new dummy user
		user = new DbUser();
		user.setUsername("admin");
		// Actual password: admin
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		// Admin user
		user.setAccess(1);
		
		// Add to array list
		users.add(user);
		
		// Create a new dummy user
		user = new DbUser();
		user.setUsername("employee");
		// Actual password: employee
		user.setPassword("fa5473530e4d1a5a1e1eb53d2fedb10c");
		// Regular user
		user.setAccess(2);
		
		// Add to array list
		users.add(user);
		
		// Create a new dummy user
		user = new DbUser();
		user.setUsername("customer");
		// Actual password: customer
		user.setPassword("91ec1f9324753048c0096d036a694f86");
		// Regular user
		user.setAccess(3);
		
		// Add to array list
		users.add(user);
		
		// Create a new dummy user
		user = new DbUser();
		user.setUsername("merchant");
		// Actual password: merchant
		user.setPassword("4c94e3115c1eeb9bd1e5e4bcb0bcab4e");
		// Regular user
		user.setAccess(4);
		
		// Add to array list
		users.add(user);
		
		
		
		return users;
	}
	
}
