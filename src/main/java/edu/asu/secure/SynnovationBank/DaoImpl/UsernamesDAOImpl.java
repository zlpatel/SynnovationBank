package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.Usernames;
import edu.asu.secure.SynnovationBank.Dao.UsernamesDAO;

@Repository
public class UsernamesDAOImpl implements UsernamesDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public boolean insertUsername(String userId) {
		Session session = null;
		Usernames username = new Usernames();
		username.setUserId(userId);
		try{
			session = factory.getCurrentSession();
			session.save(username);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public boolean fetchUsername(String userId) {
		Session session = null;
		Usernames username = null;
		try {
			session = factory.getCurrentSession();
			username = (Usernames)session.get(Usernames.class, userId);
			if (username != null)
				return true;
			else
				return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return true;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}
}
