package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.User;
import edu.asu.secure.SynnovationBank.Dao.UserDaoTest;

@Repository
public class UserDaoTestImpl implements UserDaoTest {
	 
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private SessionFactory sessionFactory;
 
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
 
		logger.debug("username passed : "+ username);
		List<User> users = new ArrayList<User>();
 
		users = getSessionFactory().getCurrentSession()
			.createQuery("from User where username=:usrnm")
			.setParameter("usrnm", username).list();
 
		logger.debug("users size : "+ users.size());
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
 
	@Override
	public boolean updateOTP(String username, String email, String otp) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean checkOTP(String otp) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updatePassword(String newpassword, String username) {
		// TODO Auto-generated method stub
		String updt_passwd_hql= "UPDATE User u set u.password=:passwd WHERE u.username=:usernm";
		int queryResult=getSessionFactory().getCurrentSession().createQuery(updt_passwd_hql).setParameter("usernm", username).setParameter("passwd", newpassword).executeUpdate();
		if(queryResult>0){
			return true;
		}
		else
			return false;
		
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
 
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}