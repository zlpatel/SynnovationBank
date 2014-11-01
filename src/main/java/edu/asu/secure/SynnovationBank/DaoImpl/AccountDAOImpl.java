package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;

@Repository
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	SessionFactory factory = HibernateUtil.buildSessionFactory();

	@Override
	public boolean updateAccountBalance(Long accNo, float balance) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Account account = (Account)session.get(Account.class, accNo);
			if(account != null)
				account.setBalance(balance);
			session.update(account);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean fetchAllowAccessFlag(Long accNo) {
		try{
			Session session = factory.openSession();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.add(Restrictions.eq("account.accountNumber",accNo));
			Person person = (Person)criteria.uniqueResult();
			if(person != null && person.getAllowAccessFlag().equals("Y"))
				return true;
			else
				return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	
}
