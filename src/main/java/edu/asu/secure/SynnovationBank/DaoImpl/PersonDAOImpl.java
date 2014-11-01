package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;

@Repository
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	SessionFactory factory = HibernateUtil.buildSessionFactory();
	
	@Override
	public boolean insertUser(Person person) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(person);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(String userId, Person person) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			if(userId != null)
				session.update(userId, person);
			else
				session.update(person);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean authenticateUser(String userId, String password) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Person person = (Person)session.get(Person.class, userId);
			session.getTransaction().commit();
			if(person == null)
				return false;
			else if(person.getPassword().equals(password))
				return true;
			
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Person fetchPersonById(String userId) {
		Person person = null;
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			person = (Person)session.get(Person.class, userId);
			return person;
		}
		catch(Exception e){
			e.printStackTrace();
			return person;
		}
	}

	@Override
	public boolean updateOTP(String userId, String otp) {
		Person person = null;
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			person = (Person)session.get(Person.class, userId);
			person.setOneTimePassword(otp);
			person.setOtpExpiry(Calendar.getInstance().getTime());
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insertAccount(Person person) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(person);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	
}
