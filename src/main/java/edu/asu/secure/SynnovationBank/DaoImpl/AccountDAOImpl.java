package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;

public class AccountDAOImpl implements AccountDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();
	
	@Override
	public long insertAccount(Account account) {
		long accountNo = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			accountNo = (Long)session.save(account);
			session.getTransaction().commit();
			return accountNo;
		}
		catch(Exception e){
			e.printStackTrace();
			return accountNo;
		}
	}

	@Override
	public boolean updateAccount(Account account) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(account);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	
}
